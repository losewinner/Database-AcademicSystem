<template>
  <el-menu :default-openeds="[]" style="min-height: 100%;overflow-x:hidden" :style="{width:sideWidth+'px'}"
           background-color="rgb(48,65,86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px;line-height: 60px;text-align: center;display: flex;justify-content: center;align-items: center;flex-direction: row">
      <img src="https://img-blog.csdnimg.cn/dbc2eaa294194339a3b1d509e6739306.png#pic_center" style="height: 45px;" alt="">
      <b style="color: #51b1d5;margin-left: 15px;" v-show="!isCollapse">
        <span style="color:#3fccfc;font-weight: bolder">教务管理系统【学生】</span>
      </b>
    </div>
    <el-menu-item index="/Student/home" v-show="!isCollapse" >
      <i class="el-icon-s-home"></i><span>主页</span>
    </el-menu-item>
    <el-submenu v-show="!isCollapse" text-color="#fff"
                active-text-color="#ffd04b">
      <template #title>
        <i class="el-icon-info"></i>
        <span >操作</span>
      </template>
      <el-menu-item index="/Student/SelectCourse">
        <i class="el-icon-s-data"></i>
        <span>选课</span>
      </el-menu-item>
      <el-menu-item index="/Student/CheckTimetable">
        <i class="el-icon-s-order"></i>
        <span>课表查询</span>
      </el-menu-item>
      <el-menu-item index="/Student/CheckScore">
        <i class="el-icon-data-analysis"></i>
        <span>成绩查询</span>
      </el-menu-item>

    </el-submenu>


  </el-menu>

</template>

<script>
export default {
  name: "AsideStudent",
  props:{
    isCollapse:Boolean,
    sideWidth:String
  }
}
</script>

<style scoped>

</style>