<template>
  <Viewer :value="value" :plugins="plugins" />
</template>

<script setup lang="ts">
import gfm from "@bytemd/plugin-gfm";
import highlight from "@bytemd/plugin-highlight";
import { Editor, Viewer } from "@bytemd/vue-next";
import { ref, withDefaults, defineProps } from "vue";

const plugins = [gfm(), highlight()];

interface Props {
  value: string;
}

withDefaults(defineProps<Props>(), {
  value: "",
});
</script>

<style></style>
