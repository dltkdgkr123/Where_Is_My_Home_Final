<template>
  <v-container height="100vh">
    <v-card height="100vh" class="overflow-y-auto overflow-x-hidden">
      <v-row>
        <div class="text--disabled text-center">board</div>
        <!-- <v-card-title class="pa-0 pl-3 my-n1"> -->
        <div class="text-h4 text-center pa-0 pl-3 my-n1">
          <v-icon color="gray" class="iconBack mt-2 ml-5" @click="goList" large>
            mdi-arrow-left-bold
          </v-icon>
          {{ article.subject }}
        </div>
        <!-- </v-card-title> -->
        <div>
          <div class="text--disabled text-caption text-center">
            {{ article.regtime }}
          </div>
        </div>
      </v-row>
      <v-divider line></v-divider>
      <v-card-text v-html="content"> </v-card-text>
      <v-divider line></v-divider>
      <!-- <v-card-actions> </v-card-actions> -->
      <div class="d-flex justify-end mb-6 text--button">
        <v-btn text @click="goModify" small> modify </v-btn>
        <v-btn text @click="goDelete" small class="mr-5"> delete </v-btn>
      </div>
    </v-card>
  </v-container>
</template>

<script>
import { getArticle } from "@/api/board";
import { mapState } from "vuex";
import { deleteArticle } from "@/api/board";
export default {
  data() {
    return {
      article: {},
      content: null,
    };
  },
  computed: {
    ...mapState("memberStore", ["userInfo"]),
  },
  created() {
    //글 번호 url에서 뽑기
    let param = this.$route.params.articleno;
    console.log("번호는?");
    console.log(param);
    //글 저장하기
    getArticle(
      param,
      ({ data }) => {
        this.article = data;
        this.content = this.article.content.replaceAll(/(\n|\r\n)/g, "<br>");
        console.log(this.article);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    onScroll() {
      console.log("스크롤중..");
    },
    goModify() {
      console.log("글 수정하러 가기");
      this.$router.replace({ name: "BoardModify", params: { article: this.article } });
    },
    goDelete() {
      console.log("글 삭제");
      console.log(this.article);
      deleteArticle(
        this.article.articleno,
        ({ data }) => {
          console.log("글 삭제 성공");
          console.log(data);
          this.$router.push({ name: "BoardList" });
        },
        (error) => {
          console.log(error);
          console.log("글 삭제 실패");
        }
      );
    },
    goList() {
      this.$router.push({ name: "BoardList" });
    },
  },
};
</script>

<style scoped>
.iconBack {
  float: left;
}
</style>
