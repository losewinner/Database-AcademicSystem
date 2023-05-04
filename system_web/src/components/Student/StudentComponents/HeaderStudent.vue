<template>
  <div style="display: flex;text-align: left; font-size: 12px;line-height: 60px;">
    <div style="flex:1;font-size: 18px">
      <span :class="collapseBthClass" style="cursor:pointer" @click="collapse"></span>
    </div>
    <el-dropdown style="cursor: pointer">
      <div style="display: flex; flex-direction: row; align-items: center; justify-content: center" >
        <span>欢迎！ 学生</span>
        <i class="el-icon-setting" style="margin-right: 5px;"></i>
      </div>
      <el-dropdown-menu>
        <span style="text-decoration: none" @click="logout">
                    <el-dropdown-item>登出</el-dropdown-item>
        </span>
      </el-dropdown-menu>
    </el-dropdown>

  </div>

</template>

<script>
export default {
  name: "HeaderStudent",
  props:{
    collapseBthClass:String,
    collapse:function(){}
  },
  methods:{
    logout(){
      this.$router.push("/login");
      localStorage.removeItem("user");
      this.$message.success("退出成功！");
    }
  }
}
</script>

<style scoped>

</style>