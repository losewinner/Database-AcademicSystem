<template>
    <div>
    <el-descriptions class="margin-top" title="个人信息" :column="4" :size="'medium'" border>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                学号
            </template>
            <span @click="changePassword">
            {{ stuinfo.studentid }}
            </span>
        </el-descriptions-item>
        <el-descriptions-item >
            <template slot="label" >
                <i class="el-icon-user"></i>
                姓名
            </template>
            {{ stuinfo.name }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                性别
            </template>
            {{ stuinfo.sex }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                学院号
            </template>
            {{ stuinfo.deptid }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                居住地
            </template>
            <span @click="changeHome">
            {{ stuinfo.home }}
            </span>
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                电话
            </template>
            <span @click="changePhone">
            {{ stuinfo.phone }}
            </span>
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                生日
            </template>
            {{ stuinfo.birth }}
        </el-descriptions-item>
    </el-descriptions>
        <el-dialog :visible.sync="dialogVisiblePW" title="修改密码">
            <el-form label-position="left" >
                <el-form-item label="请输入新密码">
                    <el-input v-model="input" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="savePassword">保存</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
        <el-dialog :visible.sync="dialogVisibleH" title="修改住址">
            <el-form label-position="left">
                <el-form-item label="请输入新住址">
                    <el-input v-model="input" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="saveHome">保存</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
        <el-dialog :visible.sync="dialogVisibleP" title="修改手机号">
            <el-form label-position="left">
                <el-form-item label="请输入新手机号">
                    <el-input v-model="input" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="savePhone">保存</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>

import axios from "axios";

export default {
  name:'Student',
    data(){
        return{
            stuinfo:[],
            input:"",
            dialogVisiblePW: false,
            dialogVisibleH: false,
            dialogVisibleP: false
        }
    },
    methods:{

        changePassword(){
            console.log("cpw")
            this.dialogVisiblePW = true
        },
        savePassword(){
            console.log("spw")
            console.log(this.input)
            if(this.input.length<3||this.input.length>15)
            {
                this.$message({
                    type: 'error',
                    message: `修改失败！密码范围不在3-15个字符内！`
                })
                this.input=""
                this.dialogVisiblePW = false
                return;
            }
            axios.post("/student/changeStuPassword?studentId="+localStorage.getItem("userid")
                +"&input="+this.input
            ).then(res=>res.data).then(res=> {
                console.log("你好吗？")
                if (res.code == "200") {
                    this.$message({
                        type: 'success',
                        message: `修改成功！`
                    })

                }
            })
            this.input=""
            this.dialogVisiblePW = false
        },
        changeHome(){
            console.log("ch")
            this.dialogVisibleH = true
        },
        saveHome(){
            console.log("sh")
            console.log(this.input)
            axios.post("/student/changeStuHome?studentId="+localStorage.getItem("userid")
                +"&input="+this.input
            ).then(res=>res.data).then(res=> {
                if (res.code == "200") {
                    this.$message({
                        type: 'success',
                        message: `修改成功！`
                    })
                }
            })
            this.stuinfo.home=this.input
            this.input=""
            this.dialogVisibleH = false
        },
        changePhone(){
            console.log("cp")
            this.dialogVisibleP = true
        },
        savePhone(){
            console.log("sp")
            console.log(this.input)
            if(this.input.length!=11)
            {
                this.$message({
                    type: 'error',
                    message: `修改失败！手机号不为11位！`
                })
                this.input=""
                this.dialogVisibleP = false
                return;
            }
            axios.post("/student/changeStuPhone?studentId="+localStorage.getItem("userid")
                +"&input="+this.input
            ).then(res=>res.data).then(res=> {
                if (res.code == "200") {
                    this.$message({
                        type: 'success',
                        message: `修改成功！`
                    })
                }
            })
            this.stuinfo.phone=this.input
            this.input=""
            this.dialogVisibleP = false
        },
    },
    created() {
        console.log("home loading")
        this.stuinfo = JSON.parse(sessionStorage.getItem("curuser"))
        localStorage.setItem("userid",this.stuinfo.studentid);
        console.log("??",localStorage.getItem("userid"));
    },
}
</script>

<style scoped>

</style>