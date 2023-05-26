import axios from "axios";
import store from "@/store";

function apiInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      "Content-Type": "application/json;carset=utf-8",
    },
  });
  instance.interceptors.request.use((config) => {
    config.headers["access-token"] = store.state.userStore.accessToken;
    config.headers["access-control-allow-origin"] = "*";

    return config;
  });

  return instance;
}

export { apiInstance };
