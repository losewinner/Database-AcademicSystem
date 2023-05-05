<template>


  <div class="wrapper">
    <div style="opacity:0.98;margin: 200px auto;background-color: #fff;width: 390px;height: 350px;padding: 20px;border-radius: 10px;
    box-shadow: gray 4px 4px 10px">
      <div style="margin: 20px 0;text-align: center;font-size: 24px;color: #153b7f"><b>SHU 选课系统</b></div>
      <el-form :rules="rules" :model="user" ref="userForm">
        <el-form-item prop="username">
          <el-input placeholder="请输入学工号" size="medium" style="margin: 10px 0;" prefix-icon="el-icon-user" v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="请输入密码" size="medium" style="margin: 10px 0;" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>
        <div style="margin: 10px 0;display: flex;justify-content: space-around;">
          <el-button type="primary" size="small" autocomplete="off" style="width: 170px" @click="login">登录</el-button>
          <el-button type="warning" size="small" autocomplete="off" style="width: 170px" >忘记密码</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script>
export default {
    name: "Login",
    data(){
          return{
            user:{
              username:"",
              password:""
            },
            rules: {
              username: [
                {required: true, message: '请输入学工号', trigger: 'blur'},
                {min: 3, max: 15, message: '长度在 3 到 15 个字符之间', trigger: 'blur'}
              ],
              password: [
                {required: true, message: '请输入密码', trigger: 'blur'},
                {min: 3, max: 15, message: '长度在 3 到 15 个字符之间', trigger: 'blur'}
              ],
            }
          }
    },
    methods:{
        //登录
        login(){
          this.$refs["userForm"].validate((valide)=>{
            if(valide){
              this.$axios.get("http://localhost:8080/login",{
                params:this.user
              }).then(res=>res.data).then(res=> {
                console.log(res)
                if(res.code==200){
                  sessionStorage.setItem("curuser",JSON.stringify(res.data.data))
                  this.$router.push("/Teacher/home")
                  this.$message.success("登录成功！");
                }
                else{
                  this.$message.error("学工号或者密码错误！")
                  return false
                }
              })
            }
          })
        },
    }
}
</script>

<style scoped>
.wrapper{
  height: 100vh;
  //background: url("https://www.shu.edu.cn/2022banner/20230226-2.jpg");
  background-size: 100% 100%;
  overflow: hidden;
}
</style>