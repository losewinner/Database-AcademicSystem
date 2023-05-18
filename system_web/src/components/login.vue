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
          <el-button type="warning" size="small" autocomplete="off" style="width: 170px" @click="changepassword">修改密码</el-button>
        </div>
      </el-form>
    </div>
    <el-dialog title="确认修改" :visible.sync="dialogFormVisible">
    <el-form label-width="100px">
      <el-form-item label="学工号">
        <el-input style="width: 200px" v-model="username"></el-input>
      </el-form-item>
      <el-form-item label="原密码">
        <el-input style="width: 200px" v-model="oldpassword" show-password></el-input>
      </el-form-item>
      <el-form-item label="新密码">
        <el-input style="width: 200px" v-model="newpassword" show-password></el-input>
      </el-form-item>
      <el-form-item label="确认密码">
        <el-input style="width: 200px" v-model="newpasswordag" show-password></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="cancel">取 消</el-button>
      <el-button type="primary" @click="confirm">确 定</el-button>
    </div>
  </el-dialog>
  </div>

</template>
<script>
export default {
    name: "Login",
    data(){
          return{
            dialogFormVisible:false,
            username:"",
            newpassword:"",
            oldpassword:"",
            newpasswordag:"",
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
              this.$axios.get("/login",{
                params:this.user
              }).then(res=>res.data).then(res=> {
                console.log(res)
                if(res.code==200){
                  sessionStorage.setItem("curuser",JSON.stringify(res.data.data));
                  console.log("wtf",res.data.data.identify);
                  if(res.data.identify == 1)
                  {
                      this.$router.push("/Admin/home")
                  }
                  else if(res.data.identify == 2){
                      this.$router.push("/Teacher/home");
                  }
                  else if(res.data.identify == 3){
                      this.$router.push("/Student/home");
                  }
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
      confirm(){
        if(this.newpassword.length<3 || this.newpassword.length>16){
          this.$message.error("密码长度应在 3 到 15 之间");
        }
        else if(this.newpassword!=this.newpasswordag){
          this.$message.error("新密码与验证密码不匹配");
        }
        else{
          this.$axios.get("/login/changepw?username="+this.username+"&password="+ this.oldpassword+"&newpassword="
              +this.newpassword).then(res=>res.data).then(res=>{
                console.log(res)
            if(res.code=="200"){
              this.$message.success("修改成功！")
            } else{
              this.$message.error("原密码错误,修改失败！");
            }
          })
        }
      },
      cancel(){
        this.dialogFormVisible=false
        this.username=""
        this.newpassword="";
        this.oldpassword="";
        this.newpasswordag="";
      },
      changepassword(){
        this.dialogFormVisible=true;
        this.username=""
        this.newpassword="";
        this.oldpassword="";
        this.newpasswordag="";
      }
    }
}
</script>

<style scoped>
.wrapper{
  height: 100vh;
  background: url("https://www.shu.edu.cn/2022banner/20230226-2.jpg");
  background-size: 100% 100%;
  overflow: hidden;
}
</style>