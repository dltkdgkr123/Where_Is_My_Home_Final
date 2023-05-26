import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";

//Vuetify-dialog
import VuetifyDialog from "vuetify-dialog";
import "vuetify-dialog/dist/vuetify-dialog.css";

import "bootstrap/dist/css/bootstrap.min.css";
import * as bootstrap from "bootstrap";
Vue.prototype.$bootstrap = bootstrap;

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");

Vue.use(VuetifyDialog, {
  context: {
    vuetify,
  },
});
