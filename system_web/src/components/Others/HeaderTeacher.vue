<template>
  <div style="display: flex;text-align: left; font-size: 12px;line-height: 60px;">
    <div style="flex:1;font-size: 18px">
      <span :class="collapseBthClass" style="cursor:pointer" @click="collapse"></span>
    </div>
    <el-dropdown style="cursor: pointer">
      <div style="display: flex; flex-direction: row; align-items: center; justify-content: center" >

        <span>{{user.nickname}}</span><i class="el-icon-arrow-down" style="margin-right: 5px;"></i>
      </div>
      <el-dropdown-menu>
        <span style="text-decoration: none" @click="userInfo"><el-dropdown-item>个人信息 </el-dropdown-item></span>
        <span style="text-decoration: none" @click="logout"><el-dropdown-item>退出 </el-dropdown-item></span>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "HeaderTeacher",
  props: {
    collapseBthClass:String,
    collapse:function(){}
  },
  data(){
    return{
      user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},
    }
  },
  methods:{
    //退出跳转同时清楚localstorage中的数据
    logout(){
      this.$router.push("/login");
      localStorage.removeItem("user");
      this.$message.success("退出成功！");
    },
    userInfo(){
      console.log("this.$route.path:",this.$route.path)
      let nowUrl=this.$route.path
      if(nowUrl[1]=='M') {
        this.$router.push("/Manage/userInfo")
      }else{
        this.$router.push("/app/info")
      }
    }
  },
}
</script>

<style scoped>

</style>