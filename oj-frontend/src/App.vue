<template>
  <div id="app">
    <template v-if="route.path.startsWith('/user')">
      <router-view></router-view>
    </template>
    <template v-else>
      <BasicLayout></BasicLayout>
    </template>
  </div>
</template>

<script setup lang="ts">
import BasicLayout from "@/layouts/BasicLayout.vue";
import { useRoute, useRouter } from "vue-router";
import { useStore } from "vuex";
import { onMounted } from "vue";

const route = useRoute();

/**
 * 全局初始化函数，有全局单次调用的代码，都可以写到这里。
 */
const doInit = () => {
  console.log(
    " ......................阿弥陀佛......................\n" +
      "                       _oo0oo_                      \n" +
      "                      o8888888o                     \n" +
      '                      88" . "88                     \n' +
      "                      (| -_- |)                     \n" +
      "                      0\\  =  /0                     \n" +
      "                   ___/‘---’\\___                   \n" +
      "                  .' \\|       |/ '.                 \n" +
      "                 / \\\\|||  :  |||// \\                \n" +
      "                / _||||| -卍-|||||_ \\               \n" +
      "               |   | \\\\\\  -  /// |   |              \n" +
      "               | \\_|  ''\\---/''  |_/ |              \n" +
      "               \\  .-\\__  '-'  ___/-. /              \n" +
      "             ___'. .'  /--.--\\  '. .'___            \n" +
      '         ."" ‘<  ‘.___\\_<|>_/___.’>’ "".          \n' +
      "       | | :  ‘- \\‘.;‘\\ _ /’;.’/ - ’ : | |        \n" +
      "         \\  \\ ‘_.   \\_ __\\ /__ _/   .-’ /  /        \n" +
      "    =====‘-.____‘.___ \\_____/___.-’___.-’=====     \n" +
      "                       ‘=---=’                      \n" +
      "                                                    \n" +
      "....................佛祖保佑 ,永无BUG..................."
  );
};
onMounted(() => {
  doInit();
});
</script>

<style>
.arco-space-item {
  margin-bottom: 0 !important;
}
</style>
