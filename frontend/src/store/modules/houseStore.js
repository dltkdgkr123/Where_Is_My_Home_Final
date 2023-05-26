import { sidoList, gugunList, dongList, houseList, houseDetail } from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [],
    guguns: [],
    dongs: [],
    houses: {},
    house: {},
  },
  getters: {},
  mutations: {
    CLEAR_SIDO_LIST(state) {
      state.sidos = [];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [];
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },

    SET_HOUSE(state, house) {
      state.house = house;
    },

    // CLEAR_HOUSE(state) {
    //   state.house = {};
    // },
  },
  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = { sido: sidoCode };
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = { gugun: gugunCode };
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseList: async ({ commit }, params) => {
      const pathVar = `${params.regCode}/${params.searchWord}/${params.deal_y}/${params.deal_m}`;

      await houseList(
        pathVar,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    getHouseDetail: ({ commit }, aptCode) => {
      houseDetail(
        aptCode,
        ({ data }) => {
          const bonbun = data.roadNameBonbun;

          // 본번 파싱 ex) 00103 -> 103
          let i = 0;
          while (i < bonbun.length) {
            if (bonbun.charAt(i) != 0) break;
            i++;
          }

          data.roadNameBonbun = bonbun.substr(i);

          commit("SET_HOUSE", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default houseStore;
