<template>
  <el-menu :default-openeds="[]" style="min-height: 100%;overflow-x: hidden;" :style="{width:sideWidth+'px'}"
           background-color="rgb(48,65,86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           router
  >
    <div style="height: 60px;line-height: 60px;text-align: center;display: flex;justify-content: center;align-items: center;flex-direction: row">
      <b style="color: #7cd9fa;margin-left: 15px;" v-show="!isCollapse">教师管理系统</b>
    </div>
    <el-menu-item index="/Teacher/home" v-show="!isCollapse">
      <i class="el-icon-s-home"></i><span>主页</span>
    </el-menu-item>
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-message"></i>
        <span>班级管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/Teacher/Results" v-show="!isCollapse">成绩登记</el-menu-item>
        <el-menu-item index="/Teacher/SignIn" v-show="!isCollapse">签到管理</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-menu-item index="/Teacher/Timetable" v-show="!isCollapse">
      <i class="el-icon-s-home"></i><span>课表</span>
    </el-menu-item>



  </el-menu>
</template>

<script>
export default {
  name: "AsideTeacher",
  props: {
    isCollapse:Boolean,
    sideWidth:String
  }
}
</script>

<style scoped>

</style>