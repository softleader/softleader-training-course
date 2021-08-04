<template>
  <div class="modal">
    <div class="modal-card">
      <h3>{{ title }}</h3>
      <button class="close-btn">&times;</button>
      <div class="modal-content">
        <slot />
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Header",
  // Receive data from parent component with Props
  // https://vuejs.org/v2/guide/components.html#Passing-Data-to-Child-Components-with-Props
  props: {
    title: {
      type: String,
      default: "",
    },
  },
};
</script>
<style lang="scss" scoped>
@import "@/assets/css/colors";
@import "@/assets/css/main";
.modal {
  background-color: rgba(black, 0.6);
  height: 100%;
  width: 100%;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 2;
  color: $primary;
}
.modal-card {
  background-color: white;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 400px;
  @include default-radius;
  padding: 10px 50px;
}
.modal-content {
  padding: 20px 0;
}
.close-btn {
  position: absolute;
  right: 50px;
  top: 25px;
  font-size: 30px;
  background: none;
  border: none;
  outline: none;
  color: $grey;
  cursor: pointer;
}
</style>
