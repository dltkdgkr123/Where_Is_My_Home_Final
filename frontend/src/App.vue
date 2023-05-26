<template>
  <v-app id="inspire">
    <v-card class="card mx-auto">
      <v-app-bar app color="white" flat>
        <a @click="goHome">Happy House</a>
        <v-avatar
          :color="$vuetify.breakpoint.smAndDown ? 'white' : 'transparent'"
          size="32"
        ></v-avatar>

        <v-tabs centered class="ml-n9" color="grey darken-1">
          <v-tab @click="goHome"> Main </v-tab>
          <v-tab @click="goAptList"> FindHouse </v-tab>
          <v-tab @click="goBoard"> Board </v-tab>
          <v-tab @click="goMyPage"> Profile </v-tab>
        </v-tabs>

        <v-avatar
          :color="$vuetify.breakpoint.smAndDown ? 'white' : 'transparent'"
          size="32"
        ></v-avatar>

        <div class="navbar-nav ml-auto">
          <li v-if="!userInfo.userid" class="nav-item">
            <a @click="goLogin" color="grey lighten-5"> login </a>
          </li>
          <template v-else>
            <li class="nav-item">
              <a @click="goLogout" cols="12" mb="6"> logout </a>
            </li>
            <li class="nav-item" cols="12" mb="6"></li>
          </template>
        </div>
      </v-app-bar>
    </v-card>

    <v-main>
      <div fluid>
        <router-view></router-view>
      </div>
    </v-main>

    <v-footer padless>
      <v-col class="text-center" cols="12">
        {{ new Date().getFullYear() }} — <strong>HAPPY HOUSE</strong>
      </v-col>
    </v-footer>
  </v-app>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "App",
  data() {
    return {
      //<<UI관련>>
      drawer: false,
      group: null,

      //<<로그인 관련>>
      loginInfo: {},
    };
  },
  mounted() {},
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    async goLogin() {
      console.log("로그인 페이지로 가기");
      this.$router.push({ name: "login" }).catch(() => {});
    },
    async goLogout() {
      await this.$store.dispatch("userStore/logout");
      this.goHome();
    },
    goHome() {
      console.log("홈페이지로 가기");
      this.$router.push({ name: "home" }).catch(() => {});
    },
    goMyPage() {
      console.log("마이페이지로 가기");
      this.$router.push({ name: "myPage" }).catch(() => {});
    },
    goBoard() {
      console.log("공지사항으로 가기");
      this.$router.push({ name: "board" }).catch(() => {});
    },
    goAptList() {
      console.log("아파트 리스트로 가기");
      this.$router.push({ name: "aptList" }).catch(() => {});
    },
  },
};
</script>

<!-- <style scoped>
div.container.container--fluid {
  margin: 0px;
  padding: 0px;
}
</style> -->
