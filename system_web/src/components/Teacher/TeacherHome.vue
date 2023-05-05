<template>
  <div>
    <el-container style="min-height: 100vh">
      <el-aside width="sideWidth+'px'" style="background-color: rgb(10,16,26);min-height: 100%;box-shadow: 2px 0 6px rgb(0 21 41/35%);">
        <AsideTeacher :isCollapse='isCollapse' :sideWidth="sideWidth"/>
      </el-aside>
      <el-container>
        <el-header style="border-bottom: 1px solid #eee">
          <HeaderTeacher :collapseBthClass="collapseBthClass" :collapse="collapse"/>
        </el-header>
        <el-main>
          <!--页签-->
          <TabTeacher :plate="plate" :func="func"></TabTeacher>
          <!--该页面的子路由会在router-view中展示 -->
          <router-view @generateTab="generateTab"/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import AsideTeacher from "@/components/Teacher/TeacherComponents/AsideTeacher.vue";
import HeaderTeacher from "@/components/Teacher/TeacherComponents/HeaderTeacher.vue";
import TabTeacher from "@/components/Teacher/TeacherComponents/TabTeacher.vue";
export default {
  name: "TeacherHome",
  data() {
    return{
      collapseBthClass:'el-icon-s-fold',
      isCollapse:false,
      sideWidth:'200',
      plate:"",
      func:"",
      teainfo:[],
    }
  },
  components: {
    AsideTeacher,
    HeaderTeacher,
    TabTeacher
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