<template>
  <v-app>
    <v-main>
      <router-view :key="$route.fullPath" />
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "App",
  methods: {
    checkPlayer() {
      this.$socket.emit("checkPlayer")
    },
  },
  created() {
    document.addEventListener("CheckPlayer", this.checkPlayer)
  },
  beforeDestroy() {
    document.removeEventListener("CheckPlayer", this.checkPlayer)
  },
}
</script>
