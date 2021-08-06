<template>
  <div id="header">
    <h1>{{ pageTitle }}</h1>
  </div>
</template>
<script>
export default {
  name: "Header",
  // Receive data from parent component with Props
  // https://vuejs.org/v2/guide/components.html#Passing-Data-to-Child-Components-with-Props
  props: {
    pageTitle: {
      type: String,
      default: "",
    },
  },
};
</script>
<style lang="scss">
@import "@/assets/css/colors";
#header {
  background-color: white;
  height: 150px;
  padding: 30px 200px;
  h1 {
    font-size: 50px;
    color: $primary;
    font-weight: 900;
  }
}
</style>
