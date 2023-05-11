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
                                      @click="stuEdit(scope.row)">编辑</el-button>
                              <el-button
                                      size="mini"
                                      type="danger"
                                      @click="stuDelete(scope.$index, scope.row)">删除</el-button>
                          </template>
                      </el-table-column>
                  </el-table>
              </el-container>

              <el-dialog title="修改成绩" :visible.sync="stuform.isVisible">
                  <el-form :model="stuform">
                      <el-form-item label="学号" :label-width="formLabelWidth">
                          <el-input v-model="stuform.studentId" disabled= true></el-input>
                      </el-form-item>
                      <el-form-item label="学生姓名" :label-width="formLabelWidth">
                          <el-input v-model="stuform.studentName"></el-input>
                      </el-form-item>
                      <el-form-item label="性别" :label-width="formLabelWidth">
                          <el-input v-model="stuform.sex"></el-input>
                          <!--搞一个select框,不要用input-->
                      </el-form-item>
                      <el-form-item label="生日" :label-width="formLabelWidth">
                          <el-col :span="11">
                              <el-date-picker type="date" placeholder="选择日期" v-model="stuform.date1" style="width: 100%;"></el-date-picker>
                          </el-col>
                          <el-col class="line" :span="2">-</el-col>
                          <el-col :span="11">
                              <el-time-picker placeholder="选择时间" v-model="stuform.date2" style="width: 100%;"></el-time-picker>
                          </el-col>
                      </el-form-item>
                      <el-form-item label="电话" :label-width="formLabelWidth">
                          <el-input v-model="stuform.phone"></el-input>
                      </el-form-item>
                      <el-form-item label="家庭地址" :label-width="formLabelWidth">
                          <el-input v-model="stuform.home" ></el-input>
                      </el-form-item>
                      <el-form-item label="所属学院" :label-width="formLabelWidth">
                          <el-input v-model="stuform.deptName"  ></el-input>
                          <!--搞一个selection框，不要用input-->
                      </el-form-item>
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                      <el-button @click="stuform.isVisible = false">取 消</el-button>
                      <el-button type="primary" @click="confirmEdit">确 定</el-button>
                  </div>
              </el-dialog>
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
            stuform: {
                studentId: '',
                studentName: '',
                sex: '',
                date1:'',
                date2:'',
                home: '',
                phone:'',
                deptName: '',
                isVisible:false
            },
            formLabelWidth:'80px',
            currentRow:null,
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
        stuEdit(row){
            //编辑学生表单
            console.log("编辑学生",row);
            this.currentRow = row;
            this.stuform.isVisible = true;
            this.stuform.studentId = this.currentRow.studentId
            this.stuform.studentName = this.currentRow.studentName
            this.stuform.sex = this.currentRow.sex

            this.stuform.home = this.currentRow.home
            this.stuform.phone = this.currentRow.phone
            this.stuform.deptName = this.currentRow.deptName

            const [dateString, timeString] = this.currentRow.birth.split(" ");
            this.stuform.date1 = dateString
            this.stuform.date2 = timeString
            console.log(this.stuform)

        },
        confirmEdit(){
            //等会看看能不能把这个表单提交改造成适应别的表单的提交
            this.stuform.isVisible = false;
            //利用this.stuform中的新更新的东西，传回到后端数据库
            axios.post("/student/manageEditStu",{
                param:{
                    studentId:this.stuform.studentId,
                    studentName:this.stuform.studentName,
                    sex:this.stuform.sex,
                    birth:this.stuform.date1+this.stuform.date2,
                    home:this.stuform.home,

                }
            }).then(res=>res.data).then(res=>{
                console.log(res);
                if(res.code == "200"){
                    this.$message({
                        type: 'success',
                        message: `更新成功！`,
                    });
                    //更新成功后需要刷新一下
                    this.loadStuData();
                }
                else{
                    this.$message({
                        type: 'danger',
                        message: `更新失败！`,

                    });
                }
            })

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