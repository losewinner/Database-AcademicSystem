<template>
  <el-container class = "整个页面" style = "margin-top: 10px">
      <transition name = "el-zoom-in-center">
          <el-container class="学生" v-show="PageChange.StuPage" style="display: flex;flex-direction: column">
              <el-container class="查找框" style="margin-top: 10px;display: flex;flex-direction: row;width: 40%;gap:20px">

              </el-container>
              <el-container class = "学生表" style="margin-top: 10px;width: 85%">
                  <el-table
                          :data="StudentInfo.filter(data => !search ||
                          data.studentName.toLowerCase().includes(search.toLowerCase()) ||
                          data.deptName.toLowerCase().includes(search.toLowerCase()) ||
                          data.studentId.toLowerCase().includes(search.toLowerCase()))"
                          style="width: 90%;"
                          max-height="550">
                      <el-table-column type="expand">
                          <template slot-scope="props">
                              <el-form label-position="left" inline class="demo-table-expand">
                                  <el-form-item label="学号:">
                                      <span style="font-weight: bolder">{{ props.row.studentId }}</span>
                                  </el-form-item>
                                  <el-form-item label="学生姓名:">
                                      <span style="font-weight: bolder">{{ props.row.studentName }}</span>
                                  </el-form-item>
                                  <el-form-item label="性别:">
                                      <span>{{ props.row.sex }}</span>
                                  </el-form-item>
                                  <el-form-item label="生日:">
                                      <span>{{ props.row.birth }}</span>
                                  </el-form-item>
                                  <el-form-item label="家庭地址:">
                                      <span>{{ props.row.home }}</span>
                                  </el-form-item>
                                  <el-form-item label="电话号码:">
                                      <span>{{ props.row.phone }}</span>
                                  </el-form-item>
                              </el-form>
                          </template>
                      </el-table-column>
                      <el-table-column
                              label="学号"
                              prop="studentId">
                      </el-table-column>
                      <el-table-column
                              label="学生姓名"
                              prop="studentName" >
                      </el-table-column>
                      <el-table-column
                              label="所属学院"
                              prop="deptName">
                      </el-table-column>
                      <el-table-column
                              align="right">
                          <template slot="header" slot-scope="{}">
                              <el-input
                                      v-model="search"
                                      size="mini"
                                      placeholder="输入关键字搜索"/>
                          </template>
                          <template slot-scope="scope">
                              <el-button
                                      size="mini"
                                      @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                              <el-button
                                      size="mini"
                                      type="danger"
                                      @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                          </template>
                      </el-table-column>
                  </el-table>
              </el-container>
          </el-container>

      </transition>
  </el-container>
</template>

<script>
import axios from "axios";

export default {
    name: "AdminManage",
    data(){
        return{
            //大页面置换的boolean参数
            PageChange:{
                StuPage:true,
                TeaPage:false,
                CosPage:false,
            },
            pagination:{
                pagenum:1,
                pagesize:10,
                total:0,
            },
            input:{
                studentId:"",
                studentName:"",
                courseId:"",
                courseName:"",
                staffId:"",
                staffName:"",
            },
            search:"",
            StudentInfo:[],

        }
    },
    methods:{
        loadStuData(){
            console.log("加载学生信息")
            axios.get("/student/manageList?keyword="+this.search).then(res=>res.data).then(res=>{
                console.log(res.data);
                this.StudentInfo = res.data;
            })
        },
        handleEdit(index, row) {
            console.log(index, row);
            this.stuDialog = true;
        },

    },

    created() {
        this.loadStuData();
    },
}
</script>

<style scoped>
.demo-table-expand {
    font-size: 0;
}
.demo-table-expand label {
    width: 90px;
    color: #99a9bf;
}
.demo-table-expand .el-form-item {
    margin-right: 0;
    margin-left: 15px;
    margin-bottom: 0;
    width: 50%;
}
</style>