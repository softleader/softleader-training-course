<template>
  <!-- Every component must have a single root element. -->
  <div id="contact">
    <!-- Header component -->
    <Header :pageTitle="pageTitle" />
    <div class="container">
      <!-- Use `v-on:click` to listen to DOM on click events -->
      <!-- You can also use `@` to replace `v-on:`  -->
      <button class="default-btn" @click="addItem">Add</button>
      <!-- Table component -->
      <!-- listen for the $emit in the `Table` component: delete -->
      <Table :tableData="tableData" @edit="editItem" @delete="deleteItem" />
    </div>
    <!-- Modal component -->
    <!-- 1. Use the `v-if` directive to conditionally render a block. -->
    <!--    https://vuejs.org/v2/guide/conditional.html -->
    <!-- 2. listen for the $emit in the `Modal` component: close -->
    <Modal :title="modal.title" v-if="modal.show" @close="closeModal">
      <label>Name</label>
      <!-- Use the `v-model` directive to create two-way data bindings. -->
      <!-- https://vuejs.org/v2/guide/forms.html -->
      <input v-model="editData.name" />
      <label>Phone</label>
      <input v-model="editData.phone" />
      <button class="default-btn float-right" @click="saveItem">
        Save
      </button>
    </Modal>
  </div>
</template>
<script>
// Import components
import Header from "@/components/Header.vue";
import Modal from "@/components/Modal.vue";
import Table from "@/components/Table.vue";
// Import axios
// https://github.com/axios/axios
import axios from "axios";

export default {
  name: "Contact",
  // Defined components
  // https://vuejs.org/v2/guide/components.html
  components: { Header, Modal, Table },
  data() {
    return {
      pageTitle: "Contacts.",
      modal: {
        title: "",
        show: false,
      },
      tableData: [],
      editData: {
        name: "",
        phone: "",
      },
    };
  },
  // mounted hook
  // https://vuejs.org/v2/guide/instance.html
  mounted() {
    // Get tableData by fetchData funciton.
    this.fetchData();
  },
  methods: {
    fetchData() {
      let self = this;
      // Make a request for contact list.
      axios
        .get("http://localhost:3000/contact")
        .then(function(response) {
          self.tableData = response.data;
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    addItem() {
      // Show modal and change modal title to "Add.".
      this.modal = {
        title: "Add.",
        show: true,
      };
      // Clear editData.
      this.editData = {
        name: "",
        phone: "",
      };
    },
    editItem(item) {
      // Show modal and change modal title to "Edit.".
      this.modal = {
        title: "Edit.",
        show: true,
      };
      // Bring item in editData.
      this.editData = { ...item };
    },
    closeModal() {
      this.modal.show = false;
    },
    async deleteItem(item) {
      let self = this;
      // Delete contact by id.
      await axios
        .delete(`http://localhost:3000/contact/${item.id}`)
        .then(function(response) {
          console.log(response);
          self.fetchData();
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    async saveItem() {
      if (this.editData.id) {
        // Edit contact.
        await axios
          .put(
            `http://localhost:3000/contact/${this.editData.id}`,
            this.editData
          )
          .then(function(response) {
            console.log(response);
          })
          .catch(function(error) {
            console.log(error);
          });
      } else {
        // Add contact.
        // Set id with index + 1.
        this.editData.id = this.tableData.length + 1;
        await axios
          .post("http://localhost:3000/contact", this.editData)
          .then(function(response) {
            console.log(response);
          })
          .catch(function(error) {
            console.log(error);
          });
      }
      this.fetchData();
      this.closeModal();
    },
  },
};
</script>
