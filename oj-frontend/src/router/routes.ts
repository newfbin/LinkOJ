import { RouteRecordRaw } from "vue-router";
import HomeView from "@/views/ExampleView.vue";
import accessEnum from "@/access/accessEnum";
import UserLayout from "@/layouts/UserLayout.vue";
import UserLoginView from "@/views/user/UserLoginView.vue";
import UserRegisterView from "@/views/user/UserRegisterView.vue";

export const routes: Array<RouteRecordRaw> = [
  {
    path: "/user",
    name: "用户",
    component: UserLayout,
    meta: {
      hideInMenu: true,
    },
    children: [
      {
        path: "/user/login",
        name: "登录",
        component: UserLoginView,
      },
      {
        path: "/user/register",
        name: "注册",
        component: UserRegisterView,
      },
    ],
  },
  {
    path: "/add/question",
    name: "创建题目",
    component: () => import("../views/question/AddQuestionView.vue"),
    // meta: {
    //   access: AccessEnum.ADMIN,
    // },
  },
  {
    path: "/question",
    name: "浏览题目",
    component: () => import("../views/question/QuestionsView.vue"),
  },
  {
    path: "/update/question",
    name: "更新题目",
    component: () => import("../views/question/AddQuestionView.vue"),
    // meta: {
    //   access: AccessEnum.ADMIN,
    // },
  },
  {
    path: "/manage/question",
    name: "管理题目",
    component: () => import("../views/question/ManageQuestionView.vue"),
    // meta: {
    //   access: AccessEnum.ADMIN,
    // },
  },
  {
    path: "/",
    name: "浏览题目",
    component: HomeView,
  },
  // {
  //   path: "/hide",
  //   name: "隐藏页面",
  //   component: HomeView,
  //   meta: {
  //     hideInMenu: true,
  //   },
  // },
  {
    path: "/noAuth",
    name: "无权限",
    component: () => import("../views/NoAuthView.vue"),
  },
  // {
  //   path: "/admin",
  //   name: "管理员",
  //   component: () => import("../views/AdminView.vue"),
  //   meta: {
  //     //表示该页面仅管理员可见
  //     access: accessEnum.ADMIN,
  //   },
  // },
  // {
  //   path: "/about",
  //   name: "关于我的",
  //   component: () => import("../views/AboutView.vue"),
  // },
];
