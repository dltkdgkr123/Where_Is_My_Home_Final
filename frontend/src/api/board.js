import { apiInstance } from "./index.js";

const api = apiInstance();

async function listArticle(success, fail) {
  api.get(`/board`).then(success).catch(fail);
}

async function writeArticle(article, success, fail) {
  api.post(`/board`, article).then(success).catch(fail);
}

async function getArticle(articleno, success, fail) {
  api.get(`/board/${articleno}`).then(success).catch(fail);
}

async function modifyArticle(article, success, fail) {
  api.put(`/board`, article).then(success).catch(fail);
}

async function deleteArticle(articleno, success, fail) {
  api.delete(`/board/${articleno}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
