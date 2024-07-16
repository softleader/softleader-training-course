<template>
  <div>
    Hello UI
  </div>
  <div>
    <button type="button" @click="callError" :disabled="loading">call error</button>
  </div>
  <div>
    <button type="button" @click="callHello(eid)" :disabled="loading">call hello</button>
    <input v-model="eid" placeholder="EID">
  </div>
  <div>
    <button type="button" @click="callQuery(queryFilter)" :disabled="loading">call query</button>
    <table>
      <tr>
        <th>EID</th>
        <td><input v-model="queryFilter.eid" placeholder="EID"></td>
      </tr>
      <tr>
        <th>Name</th>
        <td><input v-model="queryFilter.name" placeholder="Name"></td>
      </tr>
      <tr>
        <th>Result</th>
        <td>{{ queryResult }}</td>
      </tr>
    </table>

  </div>
</template>

<script>
import qs from "qs";

export default {
  data() {
    return {
      loading: false,
      eid: 0,
      queryFilter: {
        eid: null,
        name: null
      },
      queryResult: "",
    }
  },
  methods: {
    async callError() {
      try {
        this.loading = true;
        await this.$forceUpdate();
        console.log("start error api")
        await $axios.get("/api/error")
        console.log("finish error api")
      } catch (e) {
        console.log("cache error ", e);
      } finally {
        console.log("finally error api")
        this.loading = false;
      }
    },
    async callHello(eid) {
      let response = await $axios.get(`/api/member/${eid}/hello?lang=ZH`);
      alert(response.data);
    },
    async callQuery(filter) {
      let queryString = qs.stringify(filter, {
        indices: false,
        skipNulls: true,
        arrayFormat: "repeat",
      });
      let response = await $axios.get(`/api/member?${queryString}`);
      this.queryResult = response.data;
    }
  }
}

</script>