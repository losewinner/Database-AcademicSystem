<template>
    <div>
        <el-container style="min-height: 100vh">
            <el-aside width="sideWidth+'px'" style="background-color: rgb(10,16,26);min-height: 100%;box-shadow: 2px 0 6px rgb(0 21 41/35%);">
                <AsideAdmin :isCollapse='isCollapse' :sideWidth="sideWidth"/>
            </el-aside>
            <el-container>
                <el-header style="border-bottom: 1px solid #eee">
                    <HeaderAdmin :collapseBthClass="collapseBthClass" :collapse="collapse"/>
                </el-header>

                <el-main>
                    <!--页签-->
                    <TabAdmin :plate="plate" :func="func"></TabAdmin>
                    <!--该页面的子路由会在router-view中展示 -->
                    <router-view @generateTab="generateTab"/>
                </el-main>
            </el-container>
        </el-container>
    </div>

</template>

<script>
import TabAdmin from "@/components/Administrator/AdminComponents/TabAdmin.vue";
import AsideAdmin from "@/components/Administrator/AdminComponents/AsideAdmin.vue";
import HeaderAdmin from "@/components/Administrator/AdminComponents/HeaderAdmin.vue";
export default {
    name: "AdminHome",
    data(){
        return{
            collapseBthClass:'el-icon-s-fold',
            isCollapse:false,
            sideWidth:'200',
            plate:"",
            func:""
        }
    },
    components:{
        AsideAdmin,
        HeaderAdmin,
        TabAdmin
    },
    methods:{
        collapse(){
            this.isCollapse=!this.isCollapse;
            this.sideWidth=(this.isCollapse?'64':'200');
            this.collapseBthClass=(this.isCollapse?'el-icon-s-unfold':'el-icon-s-fold')
        },
        //生成页签
        generateTab(plate,func){
            console.log("generateTab触发")
            this.plate=plate
            this.func=func
        }
    }

}
</script>

<style scoped>

</style>