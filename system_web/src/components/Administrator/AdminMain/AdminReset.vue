<template>
    <el-container class="教师与学生选择" style="margin-left:20px;margin-top: 10px; display: flex;flex-direction: column;">
        <el-container class = "标题" style="margin-top: 10px;display: flex;flex-direction: column">
            <span style="font-size: 30px;font-weight: bolder;color: #51b1d5">密码重置</span>
            <span style="font-size: 20px;font-weight: bolder;color: red">【谨慎重置】</span>
        </el-container>

        <el-radio-group v-model="selectionOfSearch" style="margin-top: 20px">
            <el-radio-button label="teacher">老师</el-radio-button>
            <el-radio-button label="student">学生</el-radio-button>
        </el-radio-group>

        <el-container v-if="selectionOfSearch==='teacher'" class="老师部分" style="margin-top: 20px;display: flex;flex-direction: column" >
            <el-input
                    placeholder="请输入工号"
                    v-model="inputId"
                    style="width: 200px"
                    clearable>
            </el-input>
            <el-button type="primary" style="margin-top: 15px;width: 100px" @click="searchTea" round>搜索教师</el-button>
        </el-container>

        <el-container v-else-if="selectionOfSearch==='student'" class="学生部分" style="margin-top: 20px;display: flex;flex-direction: column" >
            <el-input
                    placeholder="请输入学号"
                    v-model="inputId"
                    style="width: 200px"
                    clearable>
            </el-input>
            <el-button type="primary" style="margin-top: 15px;width: 100px" @click="searchStu" round>搜索学生</el-button>
        </el-container>
        <el-dialog title="重置密码" :visible.sync="dialogFormVisible">
            <el-form :model="form">
                <el-form-item label="学工号" :label-width="formLabelWidth">
                    <el-input v-model="form.id" disabled></el-input>
                </el-form-item>
                <el-form-item label="姓名" :label-width="formLabelWidth">
                    <el-input v-model="form.name" disabled></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false" style="margin-right: 10px">取 消</el-button>
                <el-popconfirm
                        confirm-button-text='确定'
                        cancel-button-text='取消'
                        icon="el-icon-info"
                        icon-color="red"
                        title="确定重置吗？"
                        @confirm="confirmReset()">
                    <el-button type="danger"  slot="reference" >确 定 重 置</el-button>
                </el-popconfirm>
            </div>
        </el-dialog>
    </el-container>
</template>

<script>
import axios from "axios";

export default {
    name: "AdminReset",
    data(){
        return{
            selectionOfSearch:'',
            inputId:'',
            StaffInfo:null,
            dialogFormVisible:false,
            form:{
                id:'',
                name:'',
            },
            formLabelWidth:'80px'
        }
    },
    methods:{
        searchTea(){
            axios.get("/teacher/getTea?staffId="+this.inputId).then(res=>res.data).then(res=>{
                if(res.code == "200"&&res.data!=null){
                    console.log("教师信息",res.data);
                    this.StaffInfo = res.data;
                    this.$message({
                        type: 'success',
                        message: `查找成功！`,
                    });
                    this.form.id = this.StaffInfo.staffid;
                    this.form.name = this.StaffInfo.teachername;
                    this.dialogFormVisible = true;

                }
                else{
                    this.$message({
                        type: 'danger',
                        message: `查找失败！`,

                    });
                }
            })
        },
        searchStu(){
            axios.get("/student/getStu?studentId="+this.inputId).then(res=>res.data).then(res=>{
                if(res.code == "200"&&res.data!=null){
                    console.log("学生信息",res.data);
                    this.StaffInfo = res.data;
                    this.$message({
                        type: 'success',
                        message: `查找成功！`,
                    });
                    this.form.id = this.StaffInfo.studentid;
                    this.form.name = this.StaffInfo.name;
                    this.dialogFormVisible = true;
                }
                else{
                    this.$message({
                        type: 'danger',
                        message: `查找失败！`,

                    });
                }
            })
        },
        confirmReset(){
            //然后去login表中修改；改变formid对应的密码，密码重置为学工号
            this.$axios.get("/login/resetpw?username="+this.form.id+"&newpassword="
                +this.form.id).then(res=>res.data).then(res=>{
                console.log(res)
                if(res.code=="200"){
                    this.$message.success("重置成功！")
                } else{
                    this.$message.error("重置失败！");
                }
            })
            this.dialogFormVisible = false;
        }
    }

}
</script>

<style scoped>

</style>