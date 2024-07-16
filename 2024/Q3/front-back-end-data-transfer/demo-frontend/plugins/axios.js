// axios
import axios from "axios";

export default defineNuxtPlugin((_nuxtApp) => {
  globalThis.$axios = axios.create({
    proxy: true,
    validateStatus: (status) => {
      return status >= 200 && status < 400; // 預設值為200~300
    }
  });
})
