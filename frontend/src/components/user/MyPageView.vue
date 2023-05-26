<template>
  <v-sheet :class="[computedMargin]">
    <v-card width="1500px" max-width="1500px" class="mx-auto">
      <v-row class="fill-height">
        <v-col cols="12" sm="5">
          <v-img class="ml-3" src="../../.././public/building2.jpg" width="625px">
            <v-card-title>
              <v-spacer></v-spacer>
            </v-card-title>

            <v-spacer></v-spacer>
          </v-img>
        </v-col>
        <v-col cols="12" sm="7">
          <v-list two-line>
            <v-list-item>
              <v-list-item-icon>
                <v-icon color="gray"> mdi-account </v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>{{ userInfo.username }}</v-list-item-title>
                <v-list-item-subtitle>name</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <v-divider inset></v-divider>

            <v-list-item>
              <v-list-item-icon>
                <v-icon color="gray"> mdi-email </v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>{{ userInfo.email }}</v-list-item-title>
                <v-list-item-subtitle>email</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <v-divider inset></v-divider>

            <v-list-item>
              <v-list-item-icon>
                <v-icon color="gray"> mdi-calendar </v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>{{ userInfo.joindate }}</v-list-item-title>
                <v-list-item-subtitle>가입일</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <v-divider inset></v-divider>

            <v-list-item>
              <v-list-item-icon>
                <v-icon color="gray"> mdi-map-marker </v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>관심지역</v-list-item-title>
                <v-list-item-subtitle>관심지역을 설정해주세요</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <v-list-item class="float-right">
              <v-dialog v-model="dialog" persistent width="1000px" max-width="1500px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    @click="edit"
                    color="gray"
                    text
                    v-bind="attrs"
                    v-on="on"
                    class="padding:0px"
                  >
                    modify
                  </v-btn>
                  <!-- <v-btn @click="edit" icon class="mr-4" v-bind="attrs" v-on="on">
                    <v-icon>mdi-pencil</v-icon>
                  </v-btn> -->
                </template>
                <v-card>
                  <v-card-title>
                    <span class="text-h5">User Profile</span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-spacer></v-spacer>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="userInfo.username"
                            hint="닉네임은 2자 이상의 문자열로 만들어주세요"
                            label="Name"
                            required
                          ></v-text-field>
                        </v-col>
                        <v-spacer></v-spacer>
                        <v-col cols="12" sm="6" md="4">
                          <v-text-field
                            v-model="userInfo.email"
                            label="Email"
                            required
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="4">
                          <v-autocomplete
                            v-model="userInfo.emailDomain"
                            :items="['gmail.com', 'naver.com', 'ssafy.com']"
                            label="Domain"
                          ></v-autocomplete>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="dialog = false"> Close </v-btn>
                    <v-btn color="blue darken-1" text @click="doModify"> modify </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <v-btn color="gray" text @click="quit" class="padding:0px"> withdraw </v-btn>
            </v-list-item>
          </v-list>
        </v-col>
      </v-row>
    </v-card>
  </v-sheet>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  data() {
    return {
      //회원 정보 수정 관련
      dialog: false,
      //마진 관련
      marginDirection: "y",
      marginSize: "16",
    };
  },

  computed: {
    ...mapState("userStore", ["userInfo"]),
    computedMargin() {
      return `m${this.marginDirection}-${this.marginSize}`;
    },
  },

  methods: {
    ...mapActions("userStore", ["userQuit", "userModify"]),

    edit() {
      console.log("회원 정보 수정 시도");
    },

    doModify() {
      this.userModify(this.userInfo);
      this.dialog = false;
    },

    quit() {
      this.userQuit(this.userInfo.userid);
      console.log(this.userInfo.userid);
    },
  },
};
</script>

<style></style>
