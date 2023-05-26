import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/house/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/house/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/house/dong`, { params: params }).then(success).catch(fail);
}

function houseList(pathVar, success, fail) {
  return api.get(`/house/${pathVar}`).then(success).catch(fail);
}

function houseDetail(aptCode, success, fail) {
  api.get(`/house/${aptCode}`).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, houseList, houseDetail };
