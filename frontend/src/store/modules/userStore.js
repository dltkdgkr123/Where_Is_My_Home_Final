import http from "@/util/http";
// import jwt_decode from "jwt-decode";

// //새로고침해도 유저 정보 남아있게
// import { createVuexPersistedState } from "vue-persistedstate";
import { login, regist, quit, modify, emailSend } from "@/api/user";

const userStore = {
  namespaced: true,
  // plugins: [
  //   createVuexPersistedState({
  //     storage: window.sessionStorage,
  //   }),
  // ],
  state: () => ({
    //로그인 관련
    isLogin: false,
    //토큰 관련
    isValidToken: false,

    userInfo: {}, //로그인한 유저 정보
    accessToken: null,
    refreshToken: null,
  }),
  getters: {
    getUserName(state) {
      return state.userInfo.userName;
    },
    checkUserInfo(state) {
      return state.userInfo;
    },
    checkToken(state) {
      return state.isValidToken;
    },
  },
  mutations: {
    SET_USER_INFO(state, userInfo) {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_IS_LOGIN(state, isLogin) {
      state.isLogin = isLogin;
    },
    SET_IS_VALID_TOKEN(state, isValidToken) {
      state.isValidToken = isValidToken;
    },
    SET_ACCESS_TOKEN(state, accessToken) {
      state.accessToken = accessToken;
    },
    SET_REFRESH_TOKEN(state, refreshToken) {
      state.refreshToken = refreshToken;
    },
  },
  actions: {
    async userConfirm({ commit }, userInfo) {
      await login(
        userInfo,
        (response) => {
          const { data } = response;

          // DB에 로그인 정보가 있으면
          if (response.status == "200") {
            // 유저 정보 저장 및 관련변수 설정
            commit("SET_USER_INFO", data.userInfo);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_VALID_TOKEN", true);
            // data에서 받아온 토큰 state에 저장
            commit("SET_ACCESS_TOKEN", data["access-token"]);
            commit("SET_REFRESH_TOKEN", data["refresh-token"]);

            // // 토큰 헤더에 저장
            // http.defaults.headers.common["access-token"] = data["access-token"];
            // 토큰을 디코딩하여 userinfo에 넣는 동작
            // let decodedToken = jwt_decode(token);
            // console.log("토큰 정보:", decodedToken);
            // const { id, name } = decodedToken;
            //const { user_id, user_password } = loginInfo;
            //commit("SET_USER_INFO", { id, name });

            // 세션저장소 미사용
            // sessionStorage.setItem("access-token", data["access-token"]);
            // sessionStorage.setItem("refresh-token", data["refresh-token"]);

            // refreshToekn 후처리 구현 해야 함
            alert("로그인 되었습니다!");
          } else {
            alert("아이디/비밀번호를 확인하세요");
          }
        },
        (error) => {
          alert("요청에 실패했습니다.");
          console.log(error);
        }
      );
    },
    async logout({ commit }) {
      //서버에 로그아웃요청 => 프론트에서 처리하므로 제외
      // await http.get("/member/logout");

      //토큰 지우기
      delete http.defaults.headers.common["access-token"];

      //세션 초기화
      commit("SET_USER_INFO", {});
      commit("SET_IS_LOGIN", false);
      commit("SET_IS_VALID_TOKEN", false);
      commit("SET_ACCESS_TOKEN", null);
      commit("SET_REFRESH_TOKEN", null);
    },

    async userRegist({ commit }, userInfo) {
      console.log(commit);
      await regist(
        userInfo,
        (response) => {
          if (response.status == "200") {
            alert("회원가입 되었습니다! 로그인 해주세요.");
          }
        },
        (error) => {
          alert("중복된 아이디입니다.");
          console.log(error);
        }
      );
    },

    async userQuit({ commit }, userid) {
      if (confirm("정말 탈퇴하시겠습니까?") == true) {
        await quit(
          userid,
          (response) => {
            if (response.status == "200") {
              alert("탈퇴 되었습니다! 메인화면으로 이동합니다.");
              delete http.defaults.headers.common["access-token"];

              //세션 초기화
              commit("SET_USER_INFO", {});
              commit("SET_IS_LOGIN", false);
              commit("SET_IS_VALID_TOKEN", false);
              commit("SET_ACCESS_TOKEN", null);
              commit("SET_REFRESH_TOKEN", null);
            }
          },
          (error) => {
            alert("존재하지 않는 회원입니다.");
            console.log(error);
          }
        );
      }
    },

    async userModify({ commit }, userInfo) {
      await modify(
        userInfo,
        (response) => {
          if (response.status == "200") {
            alert("수정 되었습니다!");
            commit("SET_USER_INFO", userInfo);
          }
        },
        (error) => {
          alert("존재하지 않는 회원입니다.");
          console.log(error);
        }
      );
    },

    async sendEmail({ commit }, user) {
      await emailSend(
        user,
        (response) => {
          console.log(response.status);
          if (response.status == "200") {
            alert("이메일을 전송했습니다!");
            console.log(commit);
          }
        },
        (error) => {
          alert("해당 아이디가 존재하지 않습니다.");
          console.log(error);
        }
      );
    },
  },
};

export default userStore;
