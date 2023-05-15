<template>
    <el-container class = "整个页面" style="display: flex;flex-direction: column">
        <el-descriptions class="margin-top" title="个人信息" :column="3" :size="'medium'" border style="margin-top: 20px">
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    职工号
                </template>
                {{ Admininfo.staffid }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    姓名
                </template>
                {{ Admininfo.teachername }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    身份
                </template>
                {{ Admininfo.title }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    性别
                </template>
                {{ Admininfo.sex }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    生日
                </template>
                {{ Admininfo.birth }}
            </el-descriptions-item>
        </el-descriptions>
        <el-container class ="学期表操作" style="margin-top: 40px;display:flex;flex-direction: column" >
            <el-container class="学期表上方" style="display: flex;flex-direction: row">
                <span style="font-weight: bolder;font-size: 20px">学期信息</span>
                <el-button
                    style="margin-left: 400px"
                    size="medium"
                    type="primary"
                    @click="semesterAdd">新学期引入</el-button>
            </el-container>
            <el-table
                :data="semesterList"
                style="width: 100%"
                stripe
                max-height="440px">
                <el-table-column fixed="left"
                                 prop="semester" label="学期" width="200" >
                </el-table-column>
                <el-table-column
                    prop="status" label="状态" width="250">
                </el-table-column>
                <el-table-column  width = "170">
                    <template slot-scope="scope">
                        <el-button
                            size="medium"
                            @click="setStatus(scope.row)">编辑</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-container>
        <el-dialog title="新增学期信息" :visible.sync="dialogFormAddVisible">
            <el-form :model="semesterForm">
                <el-form-item label="学期" :label-width="formLabelWidth">
                    <el-input v-model="semesterForm.semester" ></el-input>
                </el-form-item>
                <el-form-item label="学期状态" :label-width="formLabelWidth">
                    <el-input v-model="semesterForm.status" disabled></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormAddVisible = false">取 消</el-button>
                <el-button type="primary" @click="confirmAddEdit">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="编辑学期信息" :visible.sync="dialogFormEditVisible">
            <el-form :model="semesterForm">
                <el-form-item label="学期" :label-width="formLabelWidth">
                    <el-input v-model="semesterForm.semester" disabled></el-input>
                </el-form-item>
                <el-form-item label="学期状态" :label-width="formLabelWidth">
                    <el-input v-model="semesterForm.status"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormEditVisible = false">取 消</el-button>
                <el-button type="primary" @click="confirmEditEdit">确 定</el-button>
            </div>
        </el-dialog>
    </el-container>

</template>

<script>

import axios from "axios";

export default {
    name:'Admin',
    data(){
        return{
            Admininfo:[],
            semesterList:[],
            semesterForm:{
              semester:'',
              status:'',
            },
            selectSemester:null,
            statusMessage:[
                "学期未开始，等待开课",
                "学期未开始，学生选课",
                "学期进行中",
                "学期结束，学生可查询成绩"
            ],
            dialogFormAddVisible:false,
            dialogFormEditVisible:false,
            formLabelWidth:"80px",
        }
    },
    methods:{
        loadData(){
            axios.get("/semestatus/list").then(res=>{
                this.semesterList = res.data;
                for(let i = 0;i<this.semesterList.length;i++){
                    this.semesterList[i].status = this.statusMessage[this.semesterList[i].status];
                }
            })
        },
        semesterAdd(){
            this.dialogFormAddVisible = true;
        },
        confirmAddEdit(){
            this.semesterForm.status = 0;
            axios.get("/semestatus/addSemester?semester="+this.semesterForm.semester+"&status="+this.semesterForm.status)
                .then(res=>res.data).then(res=>{
                if(res.code == "200"){
                    this.$message({
                        type: 'success',
                        message: `添加成功！`,
                    });
                    //更新成功后需要刷新一下
                    this.dialogFormAddVisible = false;
                    this.loadData();
                }
                else{
                    this.$message({
                        type: 'danger',
                        message: `添加失败！`,

                    });
                }
            })
        },
        setStatus(row){
            this.dialogFormEditVisible = true;
            this.selectSemester = row;
            console.log(this.selectSemester);
            this.semesterForm.semester = this.selectSemester.semester;
            this.semesterForm.status = this.selectSemester.status
        },
        confirmEditEdit(){
            console.log("变更学期状态",this.semesterForm.status);
            //按照规则来，0，1，2这三个状态的学期，各只能有一个，

            axios.get("/semestatus/setStatus?semester="+this.semesterForm.semester+"&status="+this.semesterForm.status)
                .then(res=>res.data).then(res=>{
                if(res.code == "200"){
                    this.$message({
                        type: 'success',
                        message: `更新成功！`,
                    });
                    //更新成功后需要刷新一下
                    this.dialogFormEditVisible = false;
                    this.loadData();
                }
                else{
                    this.$message({
                        type: 'danger',
                        message: `更新失败！`,

                    });
                }
            })
        }

    },
    created() {
        console.log("home loading")
        this.Admininfo = JSON.parse(sessionStorage.getItem("curuser"))
        this.loadData();
    },
}
</script>

<style scoped>

</style>