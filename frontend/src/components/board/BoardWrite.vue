<template>
  <div>
    <v-container>
      <v-card heigt="800px">
        <v-row>
          <div class="text--disabled text-center">board</div>
          <!-- <v-card-title class="pa-0 pl-3 my-n1"> -->
          <v-text-field
            v-model="article.subject"
            placeholder="    제목을 입력하세요.."
          ></v-text-field>
          <!-- <input type="text" name="" size="40" style="border: 0px"></input> -->
          <!-- </v-card-title> -->
          <!-- <div class="text--disabled text-center text-caption"></div> -->
        </v-row>
        <v-divider line></v-divider>
        <v-card-text fluid>
          <v-textarea
            v-model="article.content"
            solo
            placeholder="내용을 입력하세요.."
            rows="20"
            auto-grow
          >
          </v-textarea>
        </v-card-text>
        <div class="d-flex justify-end mt-n7 text--button">
          <v-btn text @click="goWrite" small> post </v-btn>
          <v-btn text @click="goDelete" small class="mr-5 mb-4"> cancle </v-btn>
        </div>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import { writeArticle } from "@/api/board";
import { mapState } from "vuex";

export default {
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    goWrite() {
      console.log("글 등록하기");
      this.article.userid = this.userInfo.userid;
      console.log(this.article);
      writeArticle(this.article, (data) => {
        console.log("등록 완료");
        console.log(data);
        this.$router.push({ name: "BoardList" });
      });
    },
    goDelete() {
      console.log("등록 취소");
      this.$router.push({ name: "BoardList" });
    },
  },
};
</script>

<style></style>
