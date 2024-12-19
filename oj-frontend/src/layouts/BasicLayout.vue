<template>
  <div id="basicLayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header">
        <GlobalHeader />
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">Link刷题平台 by newfbin</a-layout-footer>
    </a-layout>
  </div>

</template>
<script setup lang="ts">
import GlobalHeader from "@/components/GlobalHeader.vue";
</script>
<style scoped>
#basicLayout {
}

#basicLayout .header {
  margin-bottom: 16px;
  box-shadow: 1px 1px 5px #eee;
}

#basicLayout .content {
  background: linear-gradient(to right, #fefefe, #fff);
  margin-bottom: 16px;
  padding: 20px;
}

#basicLayout .footer {
  background: #efefef;
  padding: 14px;
  position: sticky; /*固定定位,元素相对于浏览器窗口定位，而不随页面滚动变化。.footer 的位置将始终保持在浏览器窗口的指定位置（这里是底部）。*/
  right: 0;
  left: 0;
  bottom: 0;
  text-align: center; /*设置 .footer 中的文本内容居中对齐。*/
}
</style>
