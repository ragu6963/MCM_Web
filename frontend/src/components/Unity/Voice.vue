<template>
  <div v-if="session" class="voice-wrap">
    <div id="video-container" style="display:none;">
      <user-video
        :stream-manager="publisher"
        @click.native="updateMainVideoStreamManager(publisher)"
      />
      <user-video
        v-for="sub in subscribers"
        :key="sub.stream.connection.connectionId"
        :stream-manager="sub"
        @click.native="updateMainVideoStreamManager(sub)"
      />
    </div>
  </div>
</template>

<script>
import axios from "axios"
import {OpenVidu} from "openvidu-browser"
import UserVideo from "./Voice/UserVideo"

axios.defaults.headers.post["Content-Type"] = "application/json"

const BASE_URL = process.env.VUE_APP_OPENVIDU_URL
const OPENVIDU_SERVER_URL = "https://" + BASE_URL + ":4443"
const OPENVIDU_SERVER_SECRET = "ssafyd102"

export default {
  name: "VoiceComponet",
  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      connectionIdSubscriber: {},
      mySessionId: "mcm",
      isInEnterAudioArea: false,
    }
  },
  computed: {
    audioDevice() {
      return this.$store.getters["audioDevice"]
    },
    userGuid() {
      return this.$store.getters["guid"]
    },
    myConnectionId() {
      return this.publisher.stream.connection.connectionId
    },
    isLobby() {
      return this.$store.getters["isLobby"]
    },
  },
  components: {
    UserVideo,
  },
  mounted() {
    document.addEventListener("Mute", this.mute)
    document.addEventListener("UnMute", this.unmute)

    this.$socket.on(
      "exitAudioArea",
      ({exitUserGuid, connectionId, enterInAudioAreaConnectionId}) => {
        if (this.isLobby) return
        if (!this.audioDevice) return

        if (exitUserGuid === this.userGuid) {
          const nickname = ""
          const message = "음성채팅 Zone에서 퇴장하셨습니다."
          const color = "rgba(255,0,0,0.5)"
          this.$store.dispatch("AddChatMessage", {nickname, message, color})
          this.publisher.publishAudio(false)

          enterInAudioAreaConnectionId.forEach((enterConnectionId) => {
            if (this.myConnectionId !== enterConnectionId) {
              const subscriber = this.connectionIdSubscriber[enterConnectionId]
              if (subscriber) subscriber.subscribeToAudio(false)
            }
          })
        }
        if (exitUserGuid !== this.userGuid) {
          const subscriber = this.connectionIdSubscriber[connectionId]
          if (subscriber) subscriber.subscribeToAudio(false)
        }
      }
    )

    this.$socket.on(
      "enterAudioArea",
      ({enterUserGuid, connectionId, enterInAudioAreaConnectionId}) => {
        if (this.isLobby) return
        if (!this.audioDevice) return

        if (enterUserGuid === this.userGuid) {
          const nickname = ""
          const message = "음성채팅 Zone에 입장하셨습니다."
          const color = "rgba(255,0,0,0.5)"
          this.$store.dispatch("AddChatMessage", {nickname, message, color})
          this.publisher.publishAudio(true)

          enterInAudioAreaConnectionId.forEach((enterConnectionId) => {
            if (this.myConnectionId !== enterConnectionId) {
              const subscriber = this.connectionIdSubscriber[enterConnectionId]
              if (subscriber) subscriber.subscribeToAudio(true)
            }
          })
        }
        if (this.isInEnterAudioArea) {
          if (enterUserGuid !== this.userGuid) {
            const subscriber = this.connectionIdSubscriber[connectionId]
            subscriber.subscribeToAudio(true)
          }
        }
      }
    )
  },
  beforeDestroy() {
    document.removeEventListener("Mute", this.mute)
    document.removeEventListener("UnMute", this.unmute)
  },
  methods: {
    mute(e) {
      if (!this.audioDevice) return

      this.isInEnterAudioArea = false

      this.$socket.emit("exitAudioArea", {
        exitUserGuid: this.userGuid,
        connectionId: this.myConnectionId,
      })
    },
    unmute(e) {
      if (!this.audioDevice) return

      this.isInEnterAudioArea = true

      this.$socket.emit("enterAudioArea", {
        enterUserGuid: this.userGuid,
        connectionId: this.myConnectionId,
      })
    },
    joinSession() {
      if (!this.audioDevice) {
        return
      }
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu()

      // --- Init a session ---
      this.session = this.OV.initSession()

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({stream}) => {
        const subscriber = this.session.subscribe(stream)

        this.subscribers.push(subscriber)
        const connectionId = subscriber.stream.connection.connectionId

        this.connectionIdSubscriber[connectionId] = subscriber
        setTimeout(() => {
          subscriber.subscribeToAudio(false)
        }, 500)
        // this.repeatAudioFalse(subscriber)
      })

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({stream}) => {
        let index = this.subscribers.indexOf(stream.streamManager, 0)
        if (index >= 0) {
          this.subscribers.splice(index, 1)
        }
      })

      // On every asynchronous exception...
      this.session.on("exception", ({exception}) => {
        console.warn(exception)
      })

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, {clientData: this.userGuid})
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            let publisher = this.OV.initPublisher(undefined, {
              audioSource: this.audioDevice, // The source of audio. If undefined default microphone
              videoSource: null,
              publishAudio: false, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: false, // Whether you want to start publishing with your video enabled or not
            })

            this.mainStreamManager = publisher
            this.publisher = publisher

            // --- Publish your stream ---

            this.session.publish(this.publisher)
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            )
          })
      })
      window.addEventListener("beforeunload", this.leaveSession)
    },

    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect()

      this.session = undefined
      this.mainStreamManager = undefined
      this.publisher = undefined
      this.subscribers = []
      this.OV = undefined

      window.removeEventListener("beforeunload", this.leaveSession)
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return
      this.mainStreamManager = stream
    },

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId)
      )
    },

    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId)
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              )
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`)
              }
              reject(error.response)
            }
          })
      })
    },

    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response))
      })
    },
  },
}
</script>

<style>
.voice-wrap {
  /* display: none; */
  position: absolute;
  top: 0px;
  left: 0px;
  z-index: 100;
}
</style>
