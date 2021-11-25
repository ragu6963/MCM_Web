<template>
  <div class="audio-select">
    <v-select
      v-model="selectAudio"
      :items="this.devices"
      item-text="label"
      item-value="deviceId"
      label="마이크 선택하기"
      @change="ChangeAudioDevice()"
    ></v-select>
    <!-- <v-btn elevation="2" @click="() => this.$router.push('Unity')">
      MCM 입장하기
    </v-btn> -->
    <v-btn elevation="2" color="primary" large @click="clickEnter()">
      입장하기
    </v-btn>
  </div>
</template>

<script>
import axios from "axios"
import {OpenVidu} from "openvidu-browser"

axios.defaults.headers.post["Content-Type"] = "application/json"

const BASE_URL = process.env.VUE_APP_OPENVIDU_URL
const OPENVIDU_SERVER_URL = "https://" + BASE_URL + ":4443"
const OPENVIDU_SERVER_SECRET = "ssafyd102"

export default {
  name: "AudioSelectorComponent",
  data() {
    return {
      devices: [{label: "음성채팅 사용 안함", deviceId: null}],
      selectAudio: null,
      width: window.innerWidth,
      roomName: "setting",
      setting: {
        audioSource: undefined,
        videoSource: null,
        publishAudio: false,
      },
    }
  },
  filters: {
    getLabel: function(value) {
      return value["label"]
    },
  },
  methods: {
    clickEnter() {
      this.leaveSession()
      this.$emit("unityStart", false)
    },
    findDevices() {
      this.OV.getDevices().then((devices) => {
        devices.forEach((d) => {
          if (d.kind === "audioinput") {
            this.devices.push(d)
          }
        })
      })
    },
    ChangeAudioDevice() {
      this.publisher.stream.outboundStreamOpts.publisherProperties.audioSource = this.selectAudio
      this.publisher.publishAudio(true)
      this.$store.dispatch("SetAudio", {audioDevice: this.selectAudio})
    },
    joinSession() {
      this.OV = new OpenVidu()
      this.session = this.OV.initSession()

      this.getToken(this.roomName).then((token) => {
        this.session
          .connect(token, this.user)
          .then(() => {
            this.findDevices()
            let publisher = this.OV.initPublisher(undefined, this.setting)
            this.publisher = publisher

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
    },
    leaveSession() {
      if (this.session) this.session.disconnect()
      this.session = undefined
      this.publisher = undefined
      this.OV = undefined
    },
    getToken(roomName) {
      return this.createSession(roomName).then((sessionId) =>
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
  created() {
    this.joinSession()
  },
}
</script>

<style>
.audio-select {
  width: 80vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
.v-input {
  max-width: 500px;
  margin-right: 2rem;
}
.v-application--is-ltr .v-messages,
.v-text-field__details {
  display: none !important;
}
.v-select__selections {
  margin-bottom: 5px;
}
.v-text-field .v-label--active {
  max-width: 133%;
  transform: translateY(-23px) scale(0.75);
  pointer-events: auto;
}
.v-btn__content {
  font-size: 17px;
}
</style>
