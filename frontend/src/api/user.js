import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/member/login`, user).then(success).catch(fail);
}

async function regist(user, success, fail) {
  console.log("user api에 있다");
  console.log(user);
  await api.post(`/member`, user).then(success).catch(fail);
}

async function quit(userid, success, fail) {
  await api.delete(`/member/${userid}`).then(success).catch(fail);
}

// 리프레쉬 토큰 동작 미구현
// async function tokenRegeneration(user, success, fail) {
//   //axios 헤더에 refresh token 셋팅
//   api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token");
//   await api.post(`/member/refresh`, user).then(success).catch(fail);
// }

async function modify(user, success, fail) {
  await api.put(`/member`, user).then(success).catch(fail);
}

async function emailSend(user, success, fail) {
  await api.get(`/member/email/${user}`).then(success).catch(fail);
}

export { login, regist, quit, modify, emailSend };
