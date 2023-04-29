import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'login',
        redirect:"/login",
    },
    {
        path:'/login',
        name:'Login',
        component:()=>import('../components/login.vue')
    },
    {
        path:'/Teacher',name:'Teacher', component:()=>import('../components/Teacher/TeacherHome.vue'),
        redirect: '/home',
        children:[
            {
                path: 'home', name: 'TeacherHome', component: () => import('../components/Teacher/Teacher.vue')
            }
        ]
    },

    {
        path:'/Admin',name:'Admin',component:()=>import('../components/Administrator/AdminHome.vue'),
        redirect: '/home',
        children:[
            {
                path:'home',name:'AdminHome',component:()=>import('../components/Administrator/Admin.vue')
            }
        ]
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})
export default router