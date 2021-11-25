import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLobby: true,
    isFocusUnity: true,
    nickname: null,
    guid: null,
    audioDevice: null,
    playerCount: null,
    chatMessages: [],
  },
  mutations: {
    JOIN_GAME(state, payload) {
      state.isLobby = payload.isLobby
      state.nickname = payload.nickname
      state.guid = payload.guid
    },
    ADD_CHAT_MESSAGE(state, payload) {
      state.chatMessages.push(payload)
    },
    SET_AUDIO(state, payload) {
      state.audioDevice = payload.audioDevice
    },
    SET_PLAYER_COUNT(state, payload) {
      state.playerCount = payload.playerCount
    },
    SET_IS_FOCUS_UNITY(state, payload) {
      state.isFocusUnity = payload.isFocusUnity
    },
  },
  actions: {
    JoinGame(context, payload) {
      context.commit("JOIN_GAME", payload)
    },
    AddChatMessage(context, payload) {
      context.commit("ADD_CHAT_MESSAGE", payload)
    },
    SetAudio(context, payload) {
      context.commit("SET_AUDIO", payload)
    },
    SetPlayerCount(context, payload) {
      context.commit("SET_PLAYER_COUNT", payload)
    },
    SetIsFocustUnity(context, payload) {
      context.commit("SET_IS_FOCUS_UNITY", payload)
    },
  },
  getters: {
    isLobby: (state) => {
      return state.isLobby
    },
    nickname: (state) => {
      return state.nickname
    },
    guid: (state) => {
      return state.guid
    },
    audioDevice: (state) => {
      return state.audioDevice
    },
    chatMessages: (state) => {
      return state.chatMessages
    },
    playerCount: (state) => {
      return state.playerCount
    },
    isFocusUnity: (state) => {
      return state.isFocusUnity
    },
  },
})
