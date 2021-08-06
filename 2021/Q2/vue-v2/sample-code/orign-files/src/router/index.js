// VueRouter : https://router.vuejs.org/
// import Vue and VueRouter
import Vue from 'vue'
import VueRouter from 'vue-router'
// and then call `Vue.use(VueRouter)`.
Vue.use(VueRouter)

// Define route components.
import Home from '../views/Home.vue'
const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
  // Please add `contact` page.
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
