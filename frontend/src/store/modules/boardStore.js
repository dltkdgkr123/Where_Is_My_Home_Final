import { listArticle /*, writeArticle, getArticle, modifyArticle */ } from "@/api/board";

const boardStore = {
  namespaced: true,
  state: () => ({
    articles: [],
  }),
  getters: {
    getArticles(state) {
      return state.articles;
    },
  },
  mutations: {
    SET_ARTICLES(state, article) {
      state.articles = article;
    },
  },
  actions: {
    async getArticleList({ commit }) {
      console.log("getArticleList에 있다");
      await listArticle(
        ({ data }) => {
          commit("SET_ARTICLES", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default boardStore;
