<template>
  <div>
    <table class="table" cellspacing="0" cellpadding="0">
      <thead>
        <tr>
          <th>No.</th>
          <th>Name</th>
          <th>Phone</th>
        </tr>
      </thead>
      <tbody>
        <!-- Use the v-for directive to render a list of items based on an array. -->
        <!-- https://vuejs.org/v2/guide/list.html -->
        <tr v-for="(item, index) in tableData" :key="index">
          <td>{{ index + 1 }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.phone }}</td>
          <td>
            <!-- Use `v-on:click` to listen to DOM on click events -->
            <!-- You can also use `@` to replace `v-on:`  -->
            <button class="edit-btn" @click="editItem(item)">Edit</button>
            <button class="delete-btn" @click="deleteItem(item)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
    <p>共 {{ total }} 筆</p>
  </div>
</template>
<script>
export default {
  name: "Table",
  // Receive data from parent component with Props
  // https://vuejs.org/v2/guide/components.html#Passing-Data-to-Child-Components-with-Props
  props: {
    tableData: {
      type: Array,
      default: () => {
        return [];
      },
    },
  },
  methods: {
    editItem(item) {
      // emit an event `edit` to the parent, with 'item'.
      // https://vuejs.org/v2/guide/components-custom-events.html
      this.$emit("edit", item);
    },
    deleteItem(item) {
      // emit an event `delete` to the parent, with 'item'.
      this.$emit("delete", item);
    },
  },
  // Computed Properties : https://vuejs.org/v2/guide/computed.html
  computed: {
    total() {
      // computed total value
      let total = this.tableData.length;
      return total;
    },
  },
};
</script>
<style lang="scss">
@import "@/assets/css/colors";
@import "@/assets/css/main";
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
</style>
