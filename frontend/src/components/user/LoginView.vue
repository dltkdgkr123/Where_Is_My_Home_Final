<template>
  <v-container class="spacing-playground pa-15" fluid>
    <v-card class="mx-auto" width="800" height="610px">
      <v-img src="../../.././public/building2.jpg" height="350px"></v-img>
      <v-form v-model="valid">
        <v-container>
          <v-row>
            <v-col cols="12" md="4">
              <v-text-field v-model="loginInfo.userid" label="ID" required></v-text-field>
            </v-col>

            <v-col cols="12" md="4">
              <v-text-field
                type="password"
                v-model="loginInfo.userpwd"
                label="Password"
                required
              ></v-text-field>
            </v-col>
          </v-row>

          <v-container class="spacing-playground pa-2" fluid>
            <v-row justify="center">
              <v-btn color="gray" @click="login" class="text-button" :class="[computedMargin]">
                Login
              </v-btn>
            </v-row>

            <template>
              <v-row justify="center">
                <v-dialog v-model="dialog" persistent width="1000px" max-width="1500px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      color="gray"
                      v-bind="attrs"
                      v-on="on"
                      class="text-button"
                      :class="[computedMargin]"
                    >
                      Regist
                    </v-btn>
                  </template>
                  <template v-slot:default="dialog">
                    <v-card>
                      <v-card-title>
                        <span class="text-h5">User Profile</span>
                      </v-card-title>
                      <v-card-text>
                        <v-container>
                          <v-row>
                            <v-col cols="12" sm="6" md="4">
                              <v-text-field
                                v-model="joinInfo.userid"
                                label="ID"
                                hint="아이디는 4자 이상의 문자열으로 만들어주세요"
                                required
                              ></v-text-field>
                              <v-text-field
                                v-model="joinInfo.userpwd"
                                label="Password"
                                type="password"
                                hint="비밀번호는 4자 이상의 문자열으로 만들어주세요"
                                required
                              ></v-text-field>
                            </v-col>
                            <v-spacer></v-spacer>
                            <v-col cols="12" sm="6" md="4">
                              <v-text-field
                                v-model="joinInfo.username"
                                hint="닉네임은 2자 이상의 문자열으로 만들어주세요"
                                label="Name"
                                required
                              ></v-text-field>
                            </v-col>
                            <v-spacer></v-spacer>
                            <v-col cols="12" sm="6" md="4">
                              <v-text-field
                                v-model="joinInfo.email"
                                label="Email"
                                required
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="6" md="4">
                              <v-autocomplete
                                v-model="joinInfo.emailDomain"
                                :items="['gmail.com', 'naver.com', 'ssafy.com']"
                                label="Domain"
                              ></v-autocomplete>
                            </v-col>
                          </v-row>
                        </v-container>
                      </v-card-text>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="dialog.value = false">
                          Close
                        </v-btn>
                        <v-btn color="blue darken-1" text @click="goJoin"> Join </v-btn>
                      </v-card-actions>
                    </v-card>
                  </template>
                </v-dialog>
              </v-row>
            </template>

            <template>
              <v-row justify="center">
                <v-dialog v-model="dialog2" persistent width="500px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      color="gray"
                      v-bind="attrs"
                      v-on="on"
                      class="text-button"
                      :class="[computedMargin]"
                    >
                      Find Password
                    </v-btn>
                  </template>
                  <v-card>
                    <v-card-title>
                      <span class="text-h5">Find Password</span>
                    </v-card-title>
                    <p class="text-caption ml-6 text-gray">
                      해당 아이디에 입력된 이메일 정보로 비밀번호 정보를 보냅니다
                    </p>
                    <v-card-text>
                      <v-container>
                        <v-row>
                          <v-col cols="12" sm="10" md="6">
                            <v-text-field
                              v-model="findId"
                              label="ID"
                              hint="아이디를 입력하세요"
                              required
                            ></v-text-field>
                          </v-col>
                        </v-row>
                      </v-container>
                    </v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="blue darken-1" text @click="dialog2 = false"> Close </v-btn>
                      <v-btn color="blue darken-1" text @click="goFindPassword(findId)">
                        Send Email
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-row>
            </template>
          </v-container>
        </v-container>
      </v-form>
    </v-card>
  </v-container>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  data: () => ({
    //로그인 관련
    valid: false,
    loginInfo: {},

    //회원 가입 관련
    dialog: false,
    joinInfo: {},

    //비밀번호 찾기 관련
    dialog2: false,
    findId: null,

    //버튼 패딩
    paddingDirection: "x",
    paddingSize: "10",
    //버튼 마진
    marginDirection: "y",
    marginSize: "2",
  }),
  computed: {
    ...mapState("userStore", ["isLogin", "isValidToken"]),

    computedPadding() {
      return `p${this.paddingDirection}-${this.paddingSize}`;
    },
    computedMargin() {
      return `m${this.marginDirection}-${this.marginSize}`;
    },
  },
  methods: {
    ...mapActions("userStore", ["userConfirm", "logout", "userRegist", "sendEmail"]),
    async login() {
      const userid = this.loginInfo.userid;
      const userpwd = this.loginInfo.userpwd;

      if (userid == null) {
        alert("아이디를 입력하세요.");
        return;
      }
      if (userpwd == null) {
        alert("비밀번호를 입력하세요.");
        return;
      }
      if (userid.length < 4) {
        alert("아이디는 4글자 이상이여야만 합니다.");
        return;
      }
      if (userpwd.length < 4) {
        alert("비밀번호는 4글자 이상이여야만 합니다.");
        return;
      }

      try {
        await this.userConfirm(this.loginInfo);
        if (this.isLogin == true) {
          this.$router.push(`/`);
        }
      } catch (error) {
        console.log(error);
      }
    },
    async goJoin() {
      const joinInfo = this.joinInfo;
      const userid = joinInfo.userid;
      const userpwd = joinInfo.userpwd;
      const username = joinInfo.username;
      const email = joinInfo.email;
      const emailDomain = joinInfo.emailDomain;

      console.log(userid);

      if (userid == null) {
        alert("아이디를 입력하세요.");
        return;
      }
      if (userpwd == null) {
        alert("비밀번호를 입력하세요.");
        return;
      }
      if (userid.length < 4) {
        alert("아이디는 4글자 이상이여야만 합니다.");
        return;
      }
      if (userpwd.length < 4) {
        alert("비밀번호는 4글자 이상이여야만 합니다.");
        return;
      }
      if (username == null) {
        alert("아이디를 입력하세요.");
        return;
      }
      if (username.length < 2) {
        alert("비밀번호를 입력하세요.");
        return;
      }
      if (email == null) {
        alert("이메일을 입력하세요.");
        return;
      }
      if (emailDomain == null) {
        alert("이메일 도메인을 선택하세요.");
        return;
      }

      const emailTmp = email;

      joinInfo.email += `@${emailDomain}`;
      // delete joinInfo.emailDomain;
      await this.userRegist(this.joinInfo);
      joinInfo.email = emailTmp;
    },

    async goFindPassword(user) {
      await this.sendEmail(user);
    },
  },
};
</script>

<style></style>
