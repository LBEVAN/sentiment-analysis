<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.wrapper {
  margin: auto;
  width: 40%;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.wrapper-50 {
  margin: auto;
  width: 50%;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.wrapper-65 {
  margin: auto;
  width: 65%;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.section {
  margin-top: 5vh;
  margin-bottom: 5vh;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.input-group-text {
  color: #fff;
  background-color: #007bff;
  border-color: #007bff;
}
</style>
