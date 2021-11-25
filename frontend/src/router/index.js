import Vue from "vue"
import VueRouter from "vue-router"
import Unity from "../views/Unity.vue"
import AudioSelector from "../views/AudioSelector.vue"
import Landing from "../views/Landing.vue"

Vue.use(VueRouter)

const routes = [
  {
    path: "/Unity",
    name: "Unity",
    component: Unity,
  },
  {
    path: "/Audio",
    name: "AudioSelector",
    component: AudioSelector,
  },
  {
    path: "/",
    name: "Landing",
    component: Landing,
  },
]

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
})

export default router
