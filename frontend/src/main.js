import Vue from "vue"
import App from "./App.vue"
import router from "./router"
import store from "./store"
import vuetify from "./plugins/vuetify"
import io from "socket.io-client"

const socketIoUrl = process.env.VUE_APP_SOCKET_IO_URL
const socket = io.connect(socketIoUrl, {
  withCredentials: true,
})

Vue.prototype.$socket = socket

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app")
