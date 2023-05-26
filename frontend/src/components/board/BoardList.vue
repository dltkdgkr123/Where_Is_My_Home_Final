<template>
  <v-container>
    <v-sheet :class="[computedMargin]" elevation="4" rounded>
      <v-data-table :headers="headers" :items="articles" sort-by="articleno" @click:row="goDetail">
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>게시판</v-toolbar-title>
            <v-divider class="mx-4" inset vertical></v-divider>
            <v-spacer></v-spacer>
            <v-btn color="white" class="mb-2" @click="goWrite"> 글작성 </v-btn>
          </v-toolbar>
        </template>
        <template v-slot:no-data>
          <v-btn color="primary"> Reset </v-btn>
        </template>
      </v-data-table>
    </v-sheet>
  </v-container>
</template>

<script>
import { listArticle } from "@/api/board";

export default {
  async created() {
    console.log(this.$store.dispatch);
    listArticle(
      ({ data }) => {
        this.articles = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "New Item" : "Edit Item";
    },
    computedMargin() {
      return `m${this.marginDirection}-${this.marginSize}`;
    },
  },
  watch: {
    dialog(val) {
      val || this.close();
    },
    dialogDelete(val) {
      val || this.closeDelete();
    },
  },
  data() {
    return {
      //글 리스트 관련
      articles: [],

      dialog: false,
      dialogDelete: false,
      //테이블 관련
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: false,
          value: "subject",
          width: "*",
        },
        { text: "작성자", value: "userid", width: "8%" },
        { text: "조회수", value: "hit", width: "8%" },
        { text: "작성일", value: "regtime", width: "10%" },
      ],
      //마진 관련
      marginDirection: "y",
      marginSize: "15",
    };
  },
  methods: {
    goDetail(event, { item }) {
      console.log("상세 정보 페이지로 가기");
      console.log(item);
      this.$router.push({ name: "BoardDetail", params: { articleno: item.articleno } });
    },
    goWrite() {
      console.log("글 쓰러 가자");
      this.$router.push({ name: "BoardWrite" });
    },
  },
};
</script>

<style></style>
