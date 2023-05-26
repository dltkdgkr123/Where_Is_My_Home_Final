<template>
  <v-container>
    <v-card height="100vh">
      <v-row>
        <div class="text--disabled text-center">board</div>
        <!-- <v-card-title class="pa-0 pl-3 my-n1"> -->
        <div class="text-h4 text-center pa-0 pl-3 my-n1">{{ article.subject }}</div>
        <!-- </v-card-title> -->
        <div class="text--disabled text-center text-caption">
          {{ article.regtime }}
        </div>
      </v-row>
      <v-divider line></v-divider>
      <v-card-text>
        <v-textarea solo v-model="article.content" height="750px"> </v-textarea>
      </v-card-text>
      <!-- <v-card-actions> </v-card-actions> -->
      <div class="d-flex justify-end mt-n7 text--button">
        <v-btn text @click="goModify" small> done </v-btn>
        <v-btn text @click="goDelete" small class="mr-5"> cancle </v-btn>
      </div>
    </v-card>
  </v-container>
</template>

<script>
import { modifyArticle } from "@/api/board";

export default {
  data() {
    return {
      article: {},
    };
  },
  async created() {
    let param = await this.$route.params.article;
    console.log(param);
    this.article = param;
  },
  methods: {
    goModify() {
      console.log("수정하기");
      console.log(this.article);
      modifyArticle(this.article, (data) => {
        console.log("수정 완료");
        console.log(data);
        this.$router.push({ name: "BoardList" });
      });
    },
    goDelete() {
      console.log("수정 취소");
      this.$router.push({ name: "BoardList" });
    },
  },
};
</script>

<style></style>
