<template>
    <el-container class ="整个开课页面" style="margin-top: 10px;display:flex;flex-direction: column;width: 100%">
        <span style="font-size: 20px;font-weight: bolder;margin-top: 10px">当前开课学期:
            <span style="color:rgba(5,119,140,0.82)">{{openCouSemester}}</span>
        </span>
        <el-container class = "步骤条" style="margin-top: 30px;display: flex;flex-direction: row">
            <el-steps space="400px" :active="step.active" finish-status="success" style="width: 90%;margin-left: 50px">
                <el-step title="选择开课列表" icon="el-icon-edit"></el-step>
                <el-step title="指定老师进行开课"></el-step>
                <el-step title="设置班级人数"></el-step>
            </el-steps>
            <el-button v-if="!step.submit" style="margin-top: 12px;margin-left: 10px" @click="next">下一步</el-button>
            <el-button v-else style="margin-top: 12px;margin-left: 10px" @click="next" type="success">提交</el-button>
        </el-container>
        <el-container class = "选择开课页面" style="width:100%;margin-top: 10px;display: flex;flex-direction: column" v-show="selCourseIsV">
            <el-container class = "表格上方" style="margin-top: 10px;display: flex;flex-direction: row;">
                <el-button
                        style="margin-left: 5px"
                        size="mini"
                        type="primary"
                        @click="courseAdd">添加选中至开课</el-button>
                <el-button
                    style="margin-left: 30px"
                    type="info"
                    size="mini"
                    @click="courseNew">新增课程</el-button>
                <el-button
                    style="margin-left: 10px"
                    type="danger"
                    size="mini"
                    @click="coursedelete">批量删除</el-button>
            </el-container>
            <el-container class = "课程列表" style="margin-top:15px;width:100%">
                <el-table
                        :data="courseList.filter(data => !courseSearch ||
                          data.courseId.toLowerCase().includes(courseSearch.toLowerCase()) ||
                          data.courseName.toLowerCase().includes(courseSearch.toLowerCase()) ||
                          data.deptName.toLowerCase().includes(courseSearch.toLowerCase()))"
                        style="width: 100%"
                        stripe
                        max-height="440px"
                        @selection-change="handleSelectionChange">
                    <el-table-column
                            type="selection"
                            width="55">
                    </el-table-column>
                    <el-table-column fixed="left"
                                     prop="courseId" label="课程号" width="150" >
                    </el-table-column>
                    <el-table-column
                            prop="courseName" label="课程名称" width="150">
                    </el-table-column>
                    <el-table-column
                            prop="credit" label="学分" width="100">
                    </el-table-column>
                    <el-table-column
                            prop="creditHours" label="学时" width="100">
                    </el-table-column>
                    <el-table-column
                            prop="deptName" label="所属学院" width="200">
                    </el-table-column>
                    <el-table-column
                            prop="ratio" label="平时分数占比" width="150">
                    </el-table-column>
                    <el-table-column  width = "270">
                        <template slot="header" slot-scope="{}">
                            <el-input
                                    v-model="courseSearch"
                                    size="mini"
                                    placeholder="输入关键字搜索"/>
                        </template>
                        <template slot-scope="scope">
                            <el-button
                                    size="mini"
                                    @click="courseEdit(scope.row)">编辑</el-button>
                        </template>
                    </el-table-column>
                </el-table>

            </el-container>
            <el-dialog title="修改课程信息" :visible.sync="dialogFormVisible">
                <el-form :model="courseForm">
                    <el-form-item label="课程号" :label-width="formLabelWidth">
                        <el-input v-model="courseForm.courseId" disabled= "true"></el-input>
                    </el-form-item>
                    <el-form-item label="课程名称" :label-width="formLabelWidth">
                        <el-input v-model="courseForm.courseName"></el-input>
                    </el-form-item>
                    <el-form-item label="学分" :label-width="formLabelWidth">
                        <el-input v-model="courseForm.credit" ></el-input>
                    </el-form-item>
                    <el-form-item label="学时" :label-width="formLabelWidth">
                        <el-input v-model="courseForm.creditHours" ></el-input>
                    </el-form-item>
                    <el-form-item label="所属学院" :label-width="formLabelWidth">
                        <el-input v-model="courseForm.deptName" disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="平时/考试占比" :label-width="100">
                        <el-input v-model="courseForm.ratio" placeholder="1代表平时与考试占比1:9，以此类推" ></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="confirmEdit">确 定</el-button>
                </div>
            </el-dialog>
            <el-dialog title="新增课程信息" :visible.sync="dialogAddNew">
                <el-form :model="courseForm">
                    <el-form-item label="课程号" :label-width="formLabelWidth">
                        <el-input v-model="courseForm.courseId" ></el-input>
                    </el-form-item>
                    <el-form-item label="课程名称" :label-width="formLabelWidth">
                        <el-input v-model="courseForm.courseName"></el-input>
                    </el-form-item>
                    <el-form-item label="学分" :label-width="formLabelWidth">
                        <el-input v-model="courseForm.credit" ></el-input>
                    </el-form-item>
                    <el-form-item label="学时" :label-width="formLabelWidth">
                        <el-input v-model="courseForm.creditHours" ></el-input>
                    </el-form-item>
                    <el-form-item label="所属学院" :label-width="formLabelWidth">
                        <el-select v-model="courseForm.deptName" placeholder="请选择">
                            <el-option v-for="item in optionDept"
                                       :key="item.value"
                                       :label="item.label"
                                       :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="平时/考试占比" :label-width="100">
                        <el-input v-model="courseForm.ratio" placeholder="1代表平时与考试占比1:9，以此类推" ></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="confirmEdit">确 定</el-button>
                </div>
            </el-dialog>

        </el-container>
        <el-container class = "选择老师页面" style="width:100%;margin-top: 10px;display: flex;flex-direction: column" v-show="selTeacherIsV">
            <el-container class = "表格上方" style="margin-top: 10px;display: flex;flex-direction: row;">
                <span style="font-size: 20px;font-weight: bolder;">开课列表</span>
                <el-button
                    style="margin-left: 835px"
                    size="mini"
                    type="danger"
                    @click="courseRollback">移除选中项</el-button>
            </el-container>
            <el-container class = "开课列表" style="margin-top:15px;width:100%">
                <el-table
                    :data="courseOpen.filter(data => !courseSearch ||
                          data.courseId.toLowerCase().includes(courseSearch.toLowerCase()) ||
                          data.courseName.toLowerCase().includes(courseSearch.toLowerCase()) ||
                          data.deptName.toLowerCase().includes(courseSearch.toLowerCase()))"
                    style="width: 100%"
                    stripe
                    max-height="440px"
                    @selection-change="handleSelectionChange">
                    <el-table-column
                        type="selection"
                        width="55">
                    </el-table-column>
                    <el-table-column fixed="left"
                                     prop="courseId" label="课程号" width="150" >
                    </el-table-column>
                    <el-table-column
                        prop="courseName" label="课程名称" width="150">
                    </el-table-column>
                    <el-table-column
                        prop="credit" label="学分" width="100">
                    </el-table-column>
                    <el-table-column
                        prop="creditHours" label="学时" width="100">
                    </el-table-column>
                    <el-table-column
                        prop="deptName" label="所属学院" width="200">
                    </el-table-column>
                    <el-table-column
                        prop="ratio" label="平时分数占比" width="150">
                    </el-table-column>
                    <el-table-column  width = "270">
                        <template slot="header" slot-scope="{}">
                            <el-input
                                v-model="courseSearch"
                                size="mini"
                                placeholder="输入关键字搜索"/>
                        </template>
                        <template slot-scope="scope">
                            <el-button
                                size="mini"
                                @click="courseEdit(scope.row)">编辑</el-button>
                        </template>
                    </el-table-column>
                </el-table>

            </el-container>
        </el-container>

    </el-container>

</template>

<script>
import axios from "axios";

export default {
    name: "AdminOpCourse",
    data(){
        return{
            step:{
                active:0,
                submit:false,
            },
            //表单
            dialogFormVisible:false,
            dialogAddNew:false,
            formLabelWidth:'80px',

            //大页面可视
            selCourseIsV:true,
            selTeacherIsV:false,
            setCouPeoIsV:false,
            message:['分配教师','设置班级情况','成功提交！学期状态改变'],
            //第一部分：选择开课列表
            openCouSemester:'',
            maxOptionDept:1,
            optionDept:[], //后端导入院系表，获得院系名字
            courseList:[],
            courseSelect:[],
            courseOpen:[],
            courseSearch:'',
            courseForm:{
                courseId:'',
                courseName:'',
                credit:0,
                creditHours:0,
                deptName:'',
                ratio:'',
            },
            selectCourRowIndex:[],
            //第二部分：选择老师
            tearcherList:[],//需要放在表单里展示，
        }
    },
    methods: {
        loadData(){
            //需要获取course表的所有信息，
            //需要获取teacher表的所有信息，过滤掉管理员
            //需要获取当前semester表中的符合开课状态学期的学期，状态码为0；

            axios.get("/course/getCourseDto")
                .then(res=>res.data).then(res=>{
                console.log("课程信息",res.data,res.total);
                this.courseList = res.data;
            });
            axios.get("/semestatus/list").then(res=>{
                console.log("学期信息",res.data);
                //找到学期状态为0的，进行开课（除了学期状态3，其他状态都只能有一个！
                this.openCouSemester = res.data.find(item=>item.status === 0).semester;
            });
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
        handleSelectionChange(val){
            //找到所有选择的行的索引
            this.courseSelect = val;
            console.log("加入排课的课程",this.courseSelect)

        },
        courseNew(){
            //添加新的课程：
            console.log("添加新的课程");
            this.courseForm.courseId = '',
            this.courseForm.courseName = '',
            this.courseForm.credit = '',
            this.courseForm.creditHours = '',
            this.courseForm.deptName = '',
            this.courseForm.ratio = '',
            this.dialogAddNew = true;
        },
        courseAdd(){
            //将课程添加进开课列表中
            this.courseOpen = [...this.courseSelect,...this.courseOpen];
            this.courseList = this.courseList.filter(course=>!this.courseSelect.includes(course));
            console.log("开课列表",this.courseOpen)
        },
        courseEdit(val){
          console.log(val);
          this.courseForm = val;
          this.dialogFormVisible = true;
        },
        confirmEdit(){
            //等会看看能不能把这个表单提交改造成适应别的表单的提交
            if(this.dialogFormVisible === true)
            {
                this.dialogFormVisible = false;
                console.log("进入确认过程",this.courseForm);
                //利用this.courseform中的新更新的东西，传回到后端数据库
                axios.post("/course/updateCourse",{
                    courseId:this.courseForm.courseId,
                    courseName:this.courseForm.courseName,
                    credit:this.courseForm.credit,
                    creditHours:this.courseForm.creditHours,
                    ratio:this.courseForm.ratio,
                }).then(res=>res.data).then(res=>{
                    console.log(res);
                    if(res.code == "200"){
                        this.$message({
                            type: 'success',
                            message: `更新成功！`,
                        });
                        //更新成功后需要刷新一下
                    }
                    else{
                        this.$message({
                            type: 'danger',
                            message: `更新失败！`,

                        });
                    }
                    this.loadData();
                })
            }
            else{
                this.dialogAddNew = false;
                console.log("进入确认过程",this.courseForm);
                //利用this.courseform中的新更新的东西，传回到后端数据库
                var dept_dict = this.optionDept.find(x=>x.value===this.courseForm.deptName);
                this.courseForm.deptName = dept_dict.label;

                axios.post("/course/insertCourse",{
                    courseId:this.courseForm.courseId,
                    courseName:this.courseForm.courseName,
                    credit:this.courseForm.credit,
                    creditHours:this.courseForm.creditHours,
                    deptName:this.courseForm.deptName,
                    ratio:this.courseForm.ratio,
                }).then(res=>res.data).then(res=>{
                    console.log(res);
                    if(res.code == "200"){
                        this.$message({
                            type: 'success',
                            message: `添加成功！`,
                        });
                        //更新成功后需要刷新一下
                    }
                    else{
                        this.$message({
                            type: 'danger',
                            message: `添加失败！`,

                        });
                    }
                    this.loadData();
                })
            }


        },
        coursedelete(){
            //不仅前端移除，且数据库选课表中也要把这个课程的元组删去。
            //通过勾选框的数据来进行删除
            console.log("删除操作",this.courseSelect);
            //删除前端页面上的数据，不需要刷新，但建议此操作在数据库操作后进行
            //因为数据库可能删除不成功
            axios.post("/course/delCourse",{
                param:{
                    DeleteList: this.courseSelect
                }
            }).then(res=>res.data).then(res=>{
                if(res.code == "200"){
                    console.log("成功删除");
                    this.courseList = this.courseList.filter(course=>!this.courseSelect.includes(course));
                    this.$message({
                        type: 'success',
                        message: `更新成功！`,
                    });
                    this.loadData();
                }
                else{
                    this.$message({
                        type: 'danger',
                        message: `更新失败！`,

                    });
                }
            });

        },
        next() {
            this.$confirm('进入下一步操作不可逆, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$message({
                    type: 'success',
                    message: this.message[this.step.active]
                });
                this.step.active++;
                //等于1的时候说明已经选择好要开的课了，
                if (this.step.active == 1){
                    this.selCourseIsV = false;
                    this.setCouPeoIsV = false;
                    this.selTeacherIsV = true;
                }//等于2的时候说明已经选择好老师了
                else if(this.step.active == 2){
                    this.selCourseIsV = false;
                    this.selTeacherIsV = false;
                    this.setCouPeoIsV = true;
                }//大于2的时候说明已经输入好开课相关信息了，弹出完成信息
                else if(this.step.active >2){
                    this.selCourseIsV = false;
                    this.selTeacherIsV = false;
                    this.setCouPeoIsV = false;
                    this.step.submit = true;
                }
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消'
                });
            });


        },
    },
    created() {
        this.loadData();
    }
}
</script>

<style scoped>

</style>
<script setup>
</script>