<template>
  <div id="contact">
    <!-- Header component -->
    <Header :pageTitle="pageTitle" />
    <div class="container">
      <button class="default-btn">Add</button>
      <!-- Please create a Table component and complete CRUD operations. -->
      <table class="table" cellspacing="0" cellpadding="0">
        <thead>
          <tr>
            <th>No.</th>
            <th>Name</th>
            <th>Phone</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>A</td>
            <td>0912345678</td>
            <td>
              <button class="edit-btn">Edit</button>
              <button class="delete-btn">
                Delete
              </button>
            </td>
          </tr>
          <tr>
            <td>2</td>
            <td>B</td>
            <td>0912345679</td>
            <td>
              <button class="edit-btn">Edit</button>
              <button class="delete-btn">
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <p>共 2 筆</p>
    </div>
    <!-- Modal component -->
    <!-- Use the `v-if` directive to conditionally render a block. -->
    <!-- https://vuejs.org/v2/guide/conditional.html -->
    <Modal :title="modal.title" v-if="modal.show">
      <label>Name</label>
      <input />
      <label>Phone</label>
      <input />
      <button class="default-btn float-right">
        Save
      </button>
    </Modal>
  </div>
</template>
<script>
// Import components
import Header from "@/components/Header.vue";
import Modal from "@/components/Modal.vue";

export default {
  name: "Contact",
  // Defined components
  // https://vuejs.org/v2/guide/components.html
  components: { Header, Modal },
  data() {
    return {
      pageTitle: "Contacts.",
      modal: {
        title: "",
        show: false,
      },
    };
  },
};
</script>
