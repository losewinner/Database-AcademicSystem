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
            },
            {
                path: 'Results', name: 'Results', component: () => import('../components/Teacher/TeacherMain/ModifyResults.vue')
            },
            {
                path: 'SignIn', name: 'SignIn', component: () => import('../components/Teacher/TeacherMain/SignIn.vue')
            },

            {
                path: 'Timetable', name: 'Timetable', component: () => import('../components/Teacher/TeacherMain/Timetable.vue')
            }
        ]
    },

    {
        path:'/Student',name:'Student',component:()=>import('../components/Student/StudentHome.vue'),
        redirect: '/home',
        children:[
            {
                path:'home',name:'StudentHome',component:()=>import('../components/Student/Student.vue')
            },
            {
                path:'SelectCourse',name:'SelectCourse',component:()=>import('../components/Student/StudentMain/SelectCourse.vue')
            },
            {
                path:'CheckScore',name:'CheckScore',component:()=>import('../components/Student/StudentMain/CheckScore.vue')
            },
            {
                path:'CheckTimetable',name:'CheckTimetable',component:()=>import('../components/Student/StudentMain/CheckTimetable.vue')
            }
        ]
    },

    {
        path:'/Admin',name:'Admin',component:()=>import('../components/Administrator/AdminHome.vue'),
        redirect: '/home',
        children:[
            {
                path:'home',name:'AdminHome',component:()=>import('../components/Administrator/Admin.vue')
            },
            {
                path:'Score',name:'AdminScore',component:()=>import('../components/Administrator/AdminMain/AdminScore.vue')
            },
            {
                path:'Rank',name:'AdminRank',component:()=>import('../components/Administrator/AdminMain/AdminRank.vue')
            },
            {
                path:'Manage',name:'AdminManage',component:()=>import('../components/Administrator/AdminMain/AdminManage.vue')
            },
            {
                path:'OpenCourse',name:'AdminOpCourse',component:()=>import('../components/Administrator/AdminMain/AdminOpCourse.vue')
            },
            {
                path:'Reset',name:'AdminReset',component:()=>import('../components/Administrator/AdminMain/AdminReset.vue')
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