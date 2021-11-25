<template>
  <div
    class="d-flex justify-center align-center"
    style="height:100vh; background-color:white; "
  >
    <Voice ref="voice" />
    <Chat
      v-if="!isLobby"
      :innerWidth="handleWidth"
      :innerHeight="handleHeight"
      @click="clickChat"
    />
    <HelpText v-if="!isFocusUnity" />
    <unity
      src="./unity/Build/Build.json"
      :width="handleWidth"
      :height="handleHeight"
      unityLoader="./unity/Build/UnityLoader.js"
      ref="unity"
    ></unity>
  </div>
</template>
<script>
import Unity from "vue-unity-webgl"
import Chat from "../components/Unity/Chat"
import Voice from "../components/Unity/Voice"
import HelpText from "../components/Unity/HelpText"

export default {
  name: "UnityComponent",
  components: {
    Unity,
    Chat,
    Voice,
    HelpText,
  },
  data() {
    return {
      height: window.innerHeight,
      width: window.innerWidth,
      playerCount: null,
      unityCanvas: null,
    }
  },
  computed: {
    isFocusUnity() {
      return this.$store.getters["isFocusUnity"]
    },
    isLobby() {
      return this.$store.getters["isLobby"]
    },
    handleHeight: {
      get() {
        return this.height
      },
      set() {
        if (window.innerHeight > 800) this.height = 800
        else {
          this.height = window.innerHeight
        }
      },
    },
    handleWidth: {
      get() {
        return this.width
      },
      set() {
        if (window.innerWidth > 1280) this.width = 1280
        else {
          this.width = window.innerWidth
        }
      },
    },
  },
  methods: {
    reload() {
      window.location.reload()
    },
    handleResize() {
      this.handleWidth = window.innerWidth
      this.handleHeight = window.innerHeight
    },
    joinGame(e) {
      const isLobby = false
      const nickname = e.detail.nickname
      const guid = e.detail.guid

      this.$store.dispatch("JoinGame", {nickname, guid, isLobby})
      this.$socket.emit("join", {nickname, guid})

      this.$refs.voice.joinSession()
    },
    recaptureInputAndFocus() {
      this.unityCanvas = document.getElementById("#canvas")
      if (this.unityCanvas) {
        this.unityCanvas.setAttribute("tabindex", "1")
        this.unityCanvas.focus()
        this.unityCanvas.addEventListener("click", () => {
          this.$store.dispatch("SetIsFocustUnity", {isFocusUnity: true})
        })
      } else setTimeout(this.recaptureInputAndFocus, 100)
    },
    clickChat() {
      console.log("clickChat")
    },
  },
  mounted() {
    this.width = window.innerWidth > 1280 ? 1280 : window.innerWidth
    this.height = window.innerHeight > 800 ? 800 : window.innerHeight

    this.$socket.on("checkPlayer", ({JoinPlayerCount}) => {
      this.$refs.unity.message("LobbyManager", "OnCheckPlayer", JoinPlayerCount)
    })

    this.recaptureInputAndFocus()
  },
  created() {
    document.addEventListener("JoinGame", this.joinGame)
    window.addEventListener("resize", this.handleResize)
  },
  beforeDestroy() {
    document.removeEventListener("JoinGame", this.joinGame)
    window.removeEventListener("resize", this.handleResize)
  },
}
</script>
<style>
.footer {
  display: none;
}
.webgl-content {
  border: 2px solid black;
}
.webgl-content:focus {
  outline: none;
}
.back-btn {
  position: absolute;
  top: 50px;
  right: 100px;
}
</style>
