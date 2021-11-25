<template>
  <div
    class="d-flex flex-row justify-center"
    style="position:absolute; top:10vh; width:100vw; z-index:1000;"
  >
    <v-btn color="red lighten-1" dark x-large elevation="2">
      캐릭터 조작을 원하시면 게임 화면을 클릭해 주세요.
    </v-btn>
  </div>
</template>

<script>
export default {
  name: "HelpText",
}
</script>
