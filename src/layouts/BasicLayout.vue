<template>
  <div id="basic-layout">
    <a-layout>
      <a-layout-header class="header">
        <GlobalHeader />
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">
        <a href="http://www.codefather.cn" target="_blank">
          编程导航 by 程序员鱼皮
        </a>
      </a-layout-footer>
    </a-layout>
  </div>
</template>
<script setup lang="ts">
import GlobalHeader from "@/components/GlobalHeader.vue";
/*import { getCurrentUser } from "@/api/user";
getCurrentUser().then((res) => {
  console.log(res);
});*/
</script>
<style scoped>
#basic-layout .footer {
  background: #efefef;
  text-align: center;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 16px;
}

#basic-layout .content {
  padding: 20px;
  margin-bottom: 20px;
  background: linear-gradient(to right, #fefefe, #fff);
}

#basic-layout .header {
  background: #fff;
  padding-inline: 20px;
  color: unset;
  margin-bottom: 16px;
}
</style>
