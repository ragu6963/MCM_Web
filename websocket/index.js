const moment = require('moment-timezone')

const { readFileSync } = require('fs')
const { createServer } = require('https')
const { Server } = require('socket.io')

const options = {
  cert: readFileSync('keys/fullchain.pem'),
  key: readFileSync('keys/privkey.pem'),
}
const httpServer = createServer(options)

const io = new Server(httpServer, {
  cors: {
    origin: ['https://k5d102.p.ssafy.io/'],
    methods: ['GET', 'POST'],
    credentials: true,
  },
  allowEIO3: true,
})

let JoinPlayer = {}
let ConnectionIdSocketId = {}
let enterInAudioAreaConnectionId = []
let now = new Date()

io.on('connection', function (socket) {
  console.log('Connect from Client: ' + socket.id)

  // 현재 플레이어 수 확인
  socket.on('checkPlayer', function () {
    const JoinPlayerCount = Object.keys(JoinPlayer).length
    socket.emit('checkPlayer', { JoinPlayerCount })
  })

  // 클라이언트 게임 접속 시
  socket.on('join', function ({ nickname, guid }) {
    const koreanTime = moment().tz('Asia/Seoul')
    console.log(
      koreanTime.format('YY년 MM월 DD일 HH시 mm분 ss초') +
        ' - Join Player: ' +
        socket.id,
    )

    // 접속 유저 추가
    JoinPlayer[socket.id] = { guid, nickname }
    const JoinPlayerCount = Object.keys(JoinPlayer).length

    console.log('Player Count : ' + JoinPlayerCount)
    console.log('------------------------------')

    // join 이벤트 방출
    socket.emit('chat', {
      nickname: '',
      message: '환영합니다. MCM 입니다.',
      color: 'rgba(0,255,0,0.5)',
    })
  })

  // 클라이언트 채팅 입력 시
  socket.on('chat', function (data) {
    // 모든 유저에게 메세지 방출
    io.emit('chat', data)
  })

  // 음성채팅 존 출입시
  socket.on('exitAudioArea', function (data) {
    const connectionId = data.connectionId

    let index = enterInAudioAreaConnectionId.indexOf(connectionId, 0)
    if (index >= 0) enterInAudioAreaConnectionId.splice(index, 1)

    io.emit('exitAudioArea', { ...data, enterInAudioAreaConnectionId })
  })
  socket.on('enterAudioArea', function (data) {
    const connectionId = data.connectionId
    ConnectionIdSocketId[socket.id] = connectionId

    enterInAudioAreaConnectionId.push(connectionId)

    io.emit('enterAudioArea', { ...data, enterInAudioAreaConnectionId })
  })

  socket.on('disconnect', function () {
    // 게임에 접속한 유저가 접속을 끊었을 때
    if (JoinPlayer.hasOwnProperty(socket.id)) {
      console.log('Exit Player: ' + socket.id)
      delete JoinPlayer[socket.id]

      const JoinPlayerCount = Object.keys(JoinPlayer).length
      console.log('Player Count : ' + JoinPlayerCount)
    }
    // 접속을 끊으면 openVidu 연결 정보도 제거
    if (ConnectionIdSocketId.hasOwnProperty(socket.id)) {
      const connectionId = ConnectionIdSocketId[socket.id]
      delete ConnectionIdSocketId[socket.id]

      let index = enterInAudioAreaConnectionId.indexOf(connectionId, 0)
      if (index >= 0) enterInAudioAreaConnectionId.splice(index, 1)

      console.log('DisConnect: ' + connectionId)
    }
    console.log('------------------------------')
  })
})

httpServer.listen(3000, () => {
  console.log('port 3000 socket server open')
})
