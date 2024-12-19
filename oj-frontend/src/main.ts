import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ArcoVue from "@arco-design/web-vue";
import "@arco-design/web-vue/dist/arco.css";
import "@/plugins/axios";
import "@/access";
import "bytemd/dist/index.css";

//完整引入arco-design组件库
createApp(App).use(ArcoVue).use(store).use(router).mount("#app");
