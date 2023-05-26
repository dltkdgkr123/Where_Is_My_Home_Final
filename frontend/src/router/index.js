import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  //홈 화면
  {
    path: "/",
    name: "home",
    component: () => import("@/views/HomeView"),
  },
  //아파트 화면
  {
    path: "/apt",
    name: "aptList",
    component: () => import("@/views/AptListView"),
    redirect: "/apt/list",
    children: [
      {
        path: "list",
        name: "HomeList",
        component: () => import("@/components/home/HomeList"),
      },
    ],
  },
  //공지사항 화면
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "BoardList",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: "detail",
        name: "BoardDetail",
        component: () => import("@/components/board/BoardDetail"),
      },
      {
        path: "write",
        name: "BoardWrite",
        component: () => import("@/components/board/BoardWrite"),
      },
      {
        path: "modify",
        name: "BoardModify",
        component: () => import("@/components/board/BoardModify"),
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/UserView"),
    children: [
      //마이페이지 화면
      {
        path: "myPage",
        name: "myPage",
        component: () => import("@/components/user/MyPageView"),
      },
      //로그인 페이지 화면
      {
        path: "login",
        name: "login",
        component: () => import("@/components/user/LoginView"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
