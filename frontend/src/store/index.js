import Vue from "vue";
import Vuex from "vuex";

import userStore from "@/store/modules/userStore";
import boardStore from "@/store/modules/boardStore";
import houseStore from "./modules/houseStore";

import { createVuexPersistedState } from "vue-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [
    createVuexPersistedState({
      //session --> 브라우저 종료시 모든 데이터 제거
      Storage: sessionStorage,
    }),
  ],
  modules: { userStore, boardStore, houseStore },
});
