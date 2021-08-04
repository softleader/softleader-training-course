<template>
  <!-- Every component must have a single root element. -->
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>
    <!-- Component matched by the route will render here. -->
    <router-view />
  </div>
</template>
<style lang="scss">
@import "@/assets/css/colors";
@import "@/assets/css/main";
#nav {
  text-align: center;
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
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
.edit-btn {
  @include action-btn;
  background-color: rgba($green, 0.3);
  color: $green;
}
.delete-btn {
  @include action-btn;
  background-color: rgba($danger, 0.3);
  color: $danger;
}
.table {
  width: 100%;
  text-align: left;
  border-spacing: 0 10px;
  thead th {
    font-size: 15px;
    color: $grey;
    padding: 5px 15px;
  }
  tbody tr {
    @include default-radius;
    background-color: white;
    margin-bottom: 10px;
    td {
      padding: 15px;
      color: $primary;
      &:first-child {
        border-radius: 7px 0 0 7px;
      }
      &:last-child {
        border-radius: 0 7px 7px 0;
      }
    }
  }
}
.float-right {
  float: right;
}
</style>
