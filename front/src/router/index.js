import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/views/Login/index.vue'
import Layout from '@/views/Layout/index.vue'
import Home from '@/views/Home/index.vue'
import Category from '@/views/Category/index.vue'
import Detail from '@/views/Detail/index.vue'
import CartList from '@/views/CartList/index.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Layout',
      component: Layout,
      children: [
        {
          path: '',
          name: 'Home',
          component: Home
        },
        {
          path: '/category',
          name: 'category',
          component: Category
        },
        // {
        //   path: '/category/sub/',
        //   name: 'subcategory',
        //   component: SubCategory
        // },
        {
          path: '/detail',
          name: 'detail',
          component: Detail
        },
                {
          path: '/cartlist',
          name: 'cartList',
          component: CartList
        },
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
  ]
})

export default router
