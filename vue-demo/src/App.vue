<template>
  <div id="app">
    <h1>{{testData}}</h1>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      testData: 'Welcome to our online-system'
    }
  },
  mounted () {}
}
</script>

<style>
#app {
  font-family: monospace, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
