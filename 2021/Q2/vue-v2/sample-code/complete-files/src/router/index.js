// VueRouter : https://router.vuejs.org/
// import Vue and VueRouter
import Vue from 'vue'
import VueRouter from 'vue-router'
// and then call `Vue.use(VueRouter)`.
Vue.use(VueRouter)

// Define route components.
import Contact from '../views/Contact.vue'
const routes = [
  {
    path: '/',
    name: 'Contact',
    component:Contact
  }
]

// Create the router instance and pass the `routes` option.
const router = new VueRouter({
  // HTML5 history mode
  // https://router.vuejs.org/guide/essentials/history-mode.html
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
