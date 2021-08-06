<template>
  <!-- Every component must have a single root element. -->
  <div id="app">
    <!-- Component matched by the route will render here. -->
    <router-view />
  </div>
</template>
<style lang="scss">
@import "@/assets/css/colors";
@import "@/assets/css/main";
body {
  margin: 0;
  background-color: $primary-background;
}
.container {
  padding: 30px 200px;
}
p {
  color: $grey;
}
input {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}
.default-btn {
  @include default-radius;
  @include btn;
  padding: 10px 30px;
  font-size: 20px;
  background-color: $blue;
  color: white;
  margin: 15px 0;
}
.float-right {
  float: right;
}
</style>
