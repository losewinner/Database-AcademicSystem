<template>
  <el-menu :default-openeds="[]" style="min-height: 100%;overflow-x: hidden;" :style="{width:sideWidth+'px'}"
           background-color="rgb(48,65,86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px;line-height: 60px;text-align: center;display: flex;justify-content: center;align-items: center;flex-direction: row">
      <img src="https://img-blog.csdnimg.cn/dbc2eaa294194339a3b1d509e6739306.png#pic_center" style="height: 45px;" alt="">
      <b style="color: #7cd9fa;margin-left: 15px;" v-show="!isCollapse">教师管理系统</b>
    </div>
    <el-menu-item index="/Teacher/home">
      <i class="el-icon-s-home"></i><span>主页</span>
      <template >
      </template>
    </el-menu-item>

  </el-menu>
</template>

<script>
export default {
  name: "AsideTeacher",
  props: {
    isCollapse: Boolean,
    sideWidth:String
  }
}
</script>

<style scoped>

</style>