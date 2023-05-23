<template>
  <el-container class = "整个页面" style = "margin-top: 10px">
      <transition name = "el-zoom-in-center">
          <el-container class="学生" v-show="PageChange.StuPage" style="display: flex;flex-direction: column">
              <el-container class="学生框" style="margin-top: 10px;width: 200px">
                  <i class="el-icon-user" style="width: 40px;font-size: 30px"></i><span style="font-size: 20px">学生信息</span>
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

                          </template>
                      </el-table-column>
                  </el-table>
              </el-container>

              <el-dialog title="修改信息" :visible.sync="stuform.isVisible">
                  <el-form :model="stuform">
                      <el-form-item label="学号" :label-width="formLabelWidth">
                          <el-input v-model="stuform.studentId" disabled= "true"></el-input>
                      </el-form-item>
                      <el-form-item label="学生姓名" :label-width="formLabelWidth">
                          <el-input v-model="stuform.studentName"></el-input>
                      </el-form-item>
                      <el-form-item label="性别" :label-width="formLabelWidth">
                          <el-select v-model="stuform.sex" placeholder="请选择性别">
                              <el-option v-for="item in optionSex"
                                         :key="item.value"
                                         :label="item.label"
                                         :value="item.value">
                              </el-option>
                          </el-select>
                      </el-form-item>
                      <el-form-item label="出生日期" :label-width="formLabelWidth">
                          <el-col :span="11">
                              <el-date-picker type="date" placeholder="选择日期" v-model="stuform.birth" format="yyyy-MM-dd" value-format="yyyy-MM-dd"></el-date-picker>
                          </el-col>
                          <el-col class="line" :span="2">-</el-col>
                      </el-form-item>
                      <el-form-item label="电话" :label-width="formLabelWidth">
                          <el-input v-model="stuform.phone"></el-input>
                      </el-form-item>
                      <el-form-item label="家庭地址" :label-width="formLabelWidth">
                          <el-input v-model="stuform.home" ></el-input>
                      </el-form-item>
                      <el-form-item label="所属学院" :label-width="formLabelWidth">
                          <el-select v-model="stuform.deptName" placeholder="请选择">
                              <el-option v-for="item in optionDept"
                                         :key="item.value"
                                         :label="item.label"
                                         :value="item.value">
                              </el-option>
                          </el-select>
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
                birth:'',
                home: '',
                phone:'',
                deptName: '',
                isVisible:false
            },
            formLabelWidth:'80px',
            currentRow:null,
            maxOptionDept:1,
            optionDept:[], //后端导入院系表，获得院系名字
            optionSex:[{
                value:'选项1',
                label:'男'
            }, {
                value: '选项2',
                label: '女'
            }]
        }
    },
    methods:{
        loadData(){
            this.loadStuData();
            this.loadDeptData();
        },
        loadDeptData(){
            console.log("加载院系信息");
            this.maxOptionDept = 1;
            this.optionDept = [];
            //获取学院信息
            axios.get("/dept/list").then(res=>{
                console.log("院系",res.data);    //获取成功
                for(const item of res.data)
                {
                    let newDict={};
                    newDict['value'] = '选项'+this.maxOptionDept;
                    newDict['label'] = item.deptname;
                    this.optionDept.push(newDict);
                    this.maxOptionDept+=1;
                }
                console.log("院系选择框",this.optionDept);
            })

        },
        loadStuData(){
            console.log("加载学生信息")
            axios.get("/student/manageList?keyword="+this.search).then(res=>res.data).then(res=>{
                console.log(res.data);
                this.StudentInfo = res.data;
            });
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
            this.stuform.birth = this.currentRow.birth
            this.stuform.deptName = this.currentRow.deptName



            console.log(this.stuform)

        },
        confirmEdit(){
            //等会看看能不能把这个表单提交改造成适应别的表单的提交
            this.stuform.isVisible = false;

            //假如不改动deptName的话，下面这条就会出问题
            //如果改了deptName，下面的第一个this。stuform。deptName就是对应的选项n
            if(this.stuform.deptName.includes("选项")){
                var dept_dict = this.optionDept.find(x=>x.value===this.stuform.deptName);
                this.stuform.deptName = dept_dict.label;
            }
            if(this.stuform.sex.includes("选项")){
                var sex_dict = this.optionSex.find(x=>x.value===this.stuform.sex);
                this.stuform.sex = sex_dict.label;
            }
            console.log("进入确认过程",this.stuform);
            //利用this.stuform中的新更新的东西，传回到后端数据库
            axios.post("/student/manageEditStu",{
                pagesize:0,
                pagenum:10,
                param:{
                    studentId:this.stuform.studentId,
                    studentName:this.stuform.studentName,
                    sex:this.stuform.sex,
                    birth:this.stuform.birth,
                    home:this.stuform.home,
                    phone:this.stuform.phone,
                    deptName:this.stuform.deptName
                }
            }).then(res=>res.data).then(res=>{
                console.log(res);
                if(res.code == "200"){
                    this.$message({
                        type: 'success',
                        message: `更新成功！`,
                    });
                    //更新成功后需要刷新一下
                    this.loadData();
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
        this.loadData();
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