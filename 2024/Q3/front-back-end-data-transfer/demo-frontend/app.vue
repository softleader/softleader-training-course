<template>
  <div>
    Hello UI
  </div>
  <table>
    <tr>
      <td>
        <div>
          <button :disabled="loading" type="button" @click="callError">call error</button>
        </div>
        <div>
          <button :disabled="loading" type="button" @click="callHello(eid)">call hello</button>
          <input v-model="eid" placeholder="EID">
        </div>
        <div>
          <button :disabled="loading" type="button" @click="callQuery(queryFilter)">call query</button>
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
              <th>Type</th>
              <td>
                <span><input v-model="queryFilter.types" type="checkbox" value="A">A</span>
                <span><input v-model="queryFilter.types" type="checkbox" value="B">B</span>
                <span><input v-model="queryFilter.types" type="checkbox" value="C">C</span>
              </td>
            </tr>
            <tr>
              <th>Result</th>
              <td>{{ queryResult }}</td>
            </tr>
          </table>
          <button :disabled="loading" type="button" @click="saveMember(queryFilter)">call save</button>
          <button :disabled="loading" type="button" @click="addMember(queryFilter)">call add</button>
          <table>
            <tr>
              <th>EID</th>
              <th>Name</th>
              <th>Type</th>
            </tr>
            <tr v-for="member in members">
              <td>{{ member.eid }}</td>
              <td>{{ member.name }}</td>
              <td>{{ member.types }}</td>
            </tr>
          </table>
          <button :disabled="loading" type="button" @click="saveAllMembers(members)">call save all</button>
        </div>
      </td>
      <td>
        <button :disabled="loading" type="button" @click="callQueryMembers(queryFilter)">call query</button>
        <table>
          <tr>
            <th>EID</th>
            <th>Name</th>
            <th>Type</th>
            <th>Phone</th>
            <th>action</th>
          </tr>
          <tr v-for="member in searchResult">
            <td>{{ member.eid }}</td>
            <td>{{ member.name }}</td>
            <td>{{ member.types }}</td>
            <td>{{ member.phone }}</td>
            <td v-if="member.busy">忙碌中...</td>
            <td v-else>
              <button type="button" @click="doSomething(member)">do something</button>
            </td>
          </tr>
        </table>
        第<input v-model="page">頁, 每頁<input v-model="size">筆
      </td>
    </tr>
  </table>
  <form action="/api/member/phone" method="post" >
    <label>成員ID</label>
    <input type="text" name="memberEid"/>
    <label>手機號碼</label>
    <input type="text" name="phone"/>
    <button type="submit">送出</button>
  </form>
</template>

<script>
import qs from "qs";
import {setInterval} from "#app/compat/interval.js";

export default {
  data() {
    return {
      loading: false,
      eid: 0,
      queryFilter: {
        eid: null,
        name: null,
        types: []
      },
      page: 0,
      size: 10,
      queryResult: "",
      members: [],
      searchResult: [],
      interval: null,
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
    },
    async callQueryMembers(filter) {
      let queryString = qs.stringify({
        ...filter,
        page: this.page,
        size: this.size
      }, {
        indices: false,
        skipNulls: true,
        arrayFormat: "repeat",
      });
      let response = await $axios.get(`/api/members?${queryString}`);
      this.searchResult = response.data;
    },
    async saveMember(member) {
      let response = await $axios.post(`/api/member`, member);
      alert(`儲存成功, 目前已儲存${response.data}筆member`)
    },
    async addMember(member) {
      this.members.push({...member});
    },
    async saveAllMembers(members) {
      let response = await $axios.post(`/api/members`, members);
      alert(`儲存成功, 目前已儲存${response.data}筆member`)
    },
    async doSomething(member) {
      await $axios.put(`/api/do-something/${member.id}`);
      this.callQueryMembers(this.queryFilter);
    }
  },
  watch: {
    searchResult(val) {
      if (val.filter(m => m.busy).length === 0) {
        clearInterval(this.interval);
        this.interval = null;
      } else {
        if (this.interval == null) {
          this.interval = setInterval(() => {
            this.callQueryMembers(this.queryFilter);
          }, 500)
        }
      }
    }
  }
}

</script>