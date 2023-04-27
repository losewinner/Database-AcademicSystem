<template>
  <div id="app">
  <Admin></Admin>
  </div>
</template>

<script>
//import Login from "@/components/login.vue";


import Admin from "@/components/Administrator/Admin.vue";

export default {
  name: 'App',
  components: {
      Admin
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
  margin-bottom: 0px;
}
</style>
