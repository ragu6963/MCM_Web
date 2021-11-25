<template>
  <div
    @click="clickChat"
    v-show="isShowChat"
    class="chat-wrap"
    :style="{left: left + 'px', bottom: bottom + 'px'}"
  >
    <!-- 채팅 내역 -->
    <simplebar id="scrollBar" ref="scrollbar" class="scrollbar">
      <template v-for="(item, index) in chatMessages">
        <v-list-item
          :key="index"
          class="message-wrap"
          color="rgba(255,255,255,0.5)"
        >
          <v-list-item-content class="mb-2 mt-2 pa-0 message-wrap">
            <div class="d-flex align-center">
              <span class="font-weight-bold">{{ item.nickname }}</span>
            </div>
            <v-textarea
              :background-color="item.color"
              :value="item.message"
              rows="1"
              readonly
              auto-grow
              hide-details
              dense
              flat
              solo
            >
            </v-textarea>
          </v-list-item-content>
        </v-list-item>
      </template>
    </simplebar>
    <!-- 채팅 입력 -->
    <v-card-actions class="pa-0 mt-auto">
      <v-text-field
        style="position: sticky; bottom: 0px; width: 100%"
        tabindex="1"
        background-color="rgb(255,255,255,1)"
        v-model="inputMessage"
        placeholder="메세지를 입력하세요."
        @keyup.enter="sendMessage"
        rows="1"
        hide-details
        filled
        dense
        auto-grow
      ></v-text-field>
    </v-card-actions>
  </div>
</template>

<script>
import simplebar from "simplebar-vue"
import "simplebar/dist/simplebar.min.css"
export default {
  name: "ChatComponent",
  data() {
    return {
      inputMessage: "",
      chatMessages: this.$store.getters["chatMessages"],
      isShowChat: true,
      innerWidth: window.innerWidth,
      innerHeight: window.innerHeight,
    }
  },
  components: {
    simplebar,
  },
  computed: {
    isFocusUnity() {
      return this.$store.getters["isFocusUnity"]
    },
    left() {
      if (this.innerWidth > 1280) {
        return this.innerWidth / 2 - 640
      } else {
        return 0
      }
    },
    bottom() {
      if (this.innerHeight > 800) {
        return (this.innerHeight - 800) / 2
      } else {
        return 0
      }
    },
  },
  methods: {
    clickChat() {
      this.$store.dispatch("SetIsFocustUnity", {isFocusUnity: false})
    },
    sendMessage() {
      if (this.inputMessage.trim() == "") return

      this.$socket.emit("chat", {
        message: this.inputMessage,
        nickname: this.$store.getters["nickname"],
        guid: this.$store.getters["guid"],
        color: "rgba(255,255,255,0.7)",
      })
      this.inputMessage = ""
    },
  },
  updated() {
    this.$nextTick(() => {
      const simplebarContent = document.getElementsByClassName(
        "simplebar-content"
      )
      this.$refs.scrollbar.SimpleBar.getScrollElement().scrollTop =
        simplebarContent[0].clientHeight
    })
  },
  mounted() {
    this.$socket.on("chat", ({nickname, message, color}) => {
      this.$store.dispatch("AddChatMessage", {nickname, message, color})
    })
  },
  created() {
    document.addEventListener("JoinGame", (e) => {
      const nickname = e.detail.nickname
      const guid = e.detail.guid
      this.$store.dispatch("JoinGame", {nickname, guid})
      this.$socket.emit("join")
      this.$refs.voice.joinSession()
    })
    document.addEventListener("ShowChat", (e) => {
      this.isShowChat = true
    })
    document.addEventListener("HideChat", (e) => {
      this.isShowChat = false
    })
    window.addEventListener("resize", () => {
      this.innerWidth = window.innerWidth
      this.innerHeight = window.innerHeight
    })
  },
  beforeDestroy() {
    window.removeEventListener("resize", () => {
      this.innerWidth = window.innerWidth
      this.innerHeight = window.innerHeight
    })
  },
}
</script>

<style>
.chat-wrap {
  background-color: rgba(125, 125, 125, 0.5);
  width: 400px;
  position: absolute;
  z-index: 100;
}
.chat-wrap:focus {
  outline: none;
}
.message-list {
  overflow: hidden;
  height: 300px;
}
.scrollbar {
  height: 300px;
}
</style>
