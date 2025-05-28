<template>
  <div id="homePage">
    <h1>{{ msg }}</h1>
  </div>
</template>
<script setup lang="ts">
import { ref } from "vue";
const msg = ref("欢迎来到用户中心网上体验馆~~");
</script>
<style scoped>
#homePage {
}
</style>
