<template>
    <el-container class ="整个开课页面" style="margin-top: 10px;display:flex;flex-direction: column;width: 100%">
        <span style="font-size: 20px;font-weight: bolder;margin-top: 10px" v-show="isStepV" >当前开课学期:
            <span style="color:rgba(5,119,140,0.82)">{{openCouSemester}}</span>
        </span>
        <el-container class = "步骤条" style="margin-top: 30px;display: flex;flex-direction: row" v-show="isStepV">
            <el-steps space="400px" :active="step.active" finish-status="success" style="width: 90%;margin-left: 50px">
                <el-step title="选择开课列表" icon="el-icon-edit"></el-step>
                <el-step title="指定老师进行开课"></el-step>
                <el-step title="设置班级人数"></el-step>
            </el-steps>
            <el-button v-if="!step.submit" style="margin-top: 12px;margin-left: 10px" @click="next">下一步</el-button>
            <el-button v-else style="margin-top: 12px;margin-left: 10px" @click="submit" type="success">提交</el-button>
        </el-container>
        <el-container class = "选择开课页面" style="width:100%;margin-top: 10px;display: flex;flex-direction: column;" v-show="selCourseIsV">
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
                <el-button
                    style="margin-left: 800px"
                    size="mini"
                    type="primary"
                    @click="courseBack">退回选择</el-button>
            </el-container>
            <el-container class = "课程列表" style="margin-top:15px;width:100%;display: flex;flex-direction: row;">
                <el-container class = "未开课列表" style="display: flex;flex-direction: column;width:50%;margin-left: 10px;">
                    <span style="font-size: 20px;font-weight: bolder;margin-top: 10px;margin-left: 10px;color:rgba(155,8,60,0.6)">未选开课列表</span>
                    <el-table
                        :data="courseList.filter(data => !courseSearch ||
                          data.courseId.toLowerCase().includes(courseSearch.toLowerCase()) ||
                          data.courseName.toLowerCase().includes(courseSearch.toLowerCase()) ||
                          data.deptName.toLowerCase().includes(courseSearch.toLowerCase()))"
                        style="width: 100%"
                        stripe
                        max-height="400px"
                        @selection-change="handleSelectionChangeLeft">
                        <el-table-column
                            type="selection"
                            width="55">
                        </el-table-column>
                        <el-table-column fixed="left"
                                         prop="courseId" label="课程号" width="100%" >
                        </el-table-column>
                        <el-table-column
                            prop="courseName" label="课程名称" width="150%">
                        </el-table-column>
                        <el-table-column
                            prop="credit" label="学分" width="100%">
                        </el-table-column>
                        <el-table-column  width = "150%">
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
                <el-container class = "已开课列表" style="display: flex;flex-direction: column;width:50%;margin-left: 10px;">
                    <span style="font-size: 20px;font-weight: bolder;margin-top: 10px;margin-left: 10px;color:rgba(5,150,97,0.6)">开课列表</span>
                    <el-table
                        :data="courseOpen.filter(data => !courseSearch ||
                          data.courseId.toLowerCase().includes(courseSearch.toLowerCase()) ||
                          data.courseName.toLowerCase().includes(courseSearch.toLowerCase()) ||
                          data.deptName.toLowerCase().includes(courseSearch.toLowerCase()))"
                        style="width: 100%"
                        stripe
                        max-height="400px"
                        @selection-change="handleSelectionChangeRight">
                        <el-table-column
                            type="selection"
                            width="55">
                        </el-table-column>
                        <el-table-column fixed="left"
                                         prop="courseId" label="课程号" width="100%" >
                        </el-table-column>
                        <el-table-column
                            prop="courseName" label="课程名称" width="150%">
                        </el-table-column>
                        <el-table-column
                            prop="credit" label="学分" width="100%">
                        </el-table-column>
                        <el-table-column  width = "150%">
                            <template slot="header" slot-scope="{}">
                                <el-input
                                    v-model="courseSearch"
                                    size="mini"
                                    placeholder="输入关键字搜索"/>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-container>
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
            </el-container>
            <el-container class = "开课列表" style="margin-top:15px;width:100%">
                <el-table
                    :data="courseOpen.filter(data => !courseSearch ||
                          data.courseId.toLowerCase().includes(courseSearch.toLowerCase()) ||
                          data.courseName.toLowerCase().includes(courseSearch.toLowerCase()) ||
                          data.deptName.toLowerCase().includes(courseSearch.toLowerCase()))"
                    style="width: 100%"
                    stripe
                    max-height="440px">
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
                    <el-table-column  width = "220">
                        <template slot="header" slot-scope="{}">
                            <el-input
                                v-model="courseSearch"
                                size="mini"
                                placeholder="输入关键字搜索"/>
                        </template>
                        <template slot-scope="scope">
                            <el-button
                                size="medium"
                                @click="selectTea(scope.row)">指派老师</el-button>
                        </template>
                    </el-table-column>
                </el-table>

            </el-container>

            <el-dialog title="指派教师" :visible.sync="dialogSeleTea">
                <el-button
                    style="margin-left: 5px"
                    size="mini"
                    type="primary"
                    @click="teacherAdd">指派所选教师</el-button>
                <el-popconfirm
                    confirm-button-text="确认提交"
                    cancel-button-text="取消"
                    icon="el-icon-info"
                    icon-color="red"
                    title="是否决定提交"
                    style="margin-left: 10px"
                    @confirm="confirmAddTea">
                    <el-button slot="reference" icon = "el-icon-upload2" type="success" size="mini">提交</el-button>
                </el-popconfirm>
                <el-table :data="teacherList.filter(data => !teacherSearch ||
                          data.staffid.toLowerCase().includes(teacherSearch.toLowerCase()) ||
                          data.teachername.toLowerCase().includes(teacherSearch.toLowerCase()) ||
                          data.title.toLowerCase().includes(teacherSearch.toLowerCase()))"
                          style="width: 100%"
                          stripe
                          max-height="300px"
                          @selection-change="handleSelectionChangeTea">
                    <el-table-column
                        type="selection"
                        width="55">
                    </el-table-column>
                    <el-table-column property="staffid" label="工号" width="150"></el-table-column>
                    <el-table-column property="teachername" label="教师姓名" width="200"></el-table-column>
                    <el-table-column property="title" label="职称"></el-table-column>
                    <el-table-column  width = "150">
                        <template slot="header" slot-scope="{}">
                            <el-input
                                v-model="teacherSearch"
                                size="mini"
                                placeholder="输入关键字搜索"/>
                        </template>
                    </el-table-column>

                </el-table>
            </el-dialog>
        </el-container>
        <el-container class = "对课程进行最后的修改" style="width:100%;margin-top: 10px;display: flex;flex-direction: column" v-show="setCouPeoIsV">
            <el-container class = "表格上方" style="margin-top: 10px;display: flex;flex-direction: row;">
                <span style="font-size: 20px;font-weight: bolder;">最终开课列表</span>
            </el-container>
            <el-container class = "最终开课列表" style="margin-top:15px;width:100%">
                <el-table
                    :data="openCourseList.filter(data => !openCourseSearch ||
                          data.courseId.toLowerCase().includes(openCourseSearch.toLowerCase()) ||
                          data.courseName.toLowerCase().includes(openCourseSearch.toLowerCase()) ||
                          data.staffId.toLowerCase().includes(openCourseSearch.toLowerCase())||
                          data.teachername.toLowerCase().includes(openCourseSearch.toLowerCase()))"
                    style="width: 100%"
                    stripe
                    max-height="440px">
                    <el-table-column fixed="left"
                                     prop="courseId" label="课程号" width="150" >
                    </el-table-column>
                    <el-table-column
                        prop="courseName" label="课程名称" width="150">
                    </el-table-column>
                    <el-table-column
                        prop="staffId" label="工号" width="100">
                    </el-table-column>
                    <el-table-column
                        prop="teachername" label="教师姓名" width="100">
                    </el-table-column>
                    <el-table-column
                        prop="classTime" label="上课时间" width="200">
                    </el-table-column>
                    <el-table-column
                        prop="volume" label="班级容量" width="150">
                    </el-table-column>
                    <el-table-column  width = "220">
                        <template slot="header" slot-scope="{}">
                            <el-input
                                v-model="openCourseSearch"
                                size="mini"
                                placeholder="输入关键字搜索"/>
                        </template>
                        <template slot-scope="scope">
                            <el-button
                                size="medium"
                                @click="openCouEdit(scope.row)">编辑</el-button>
                        </template>
                    </el-table-column>
                </el-table>

            </el-container>
            <el-dialog title="修改开课信息" :visible.sync="dialogOpenCou">
                <el-form :model="openCouForm">
                    <el-form-item label="课程号" :label-width="formLabelWidth">
                        <el-input v-model="openCouForm.courseId" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="课程名称" :label-width="formLabelWidth">
                        <el-input v-model="openCouForm.courseName" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="工号" :label-width="formLabelWidth">
                        <el-input v-model="openCouForm.staffId" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="教师姓名" :label-width="formLabelWidth">
                        <el-input v-model="openCouForm.staffName" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="上课时间" :label-width="formLabelWidth">
                        <el-input v-model="openCouForm.classTime"></el-input>
                    </el-form-item>
                    <el-form-item label="班级容量" :label-width="formLabelWidth">
                        <el-input v-model="openCouForm.volume" ></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogOpenCou = false">取 消</el-button>
                    <el-button type="primary" @click="confirmOpenCouEdit">确 定</el-button>
                </div>
            </el-dialog>
        </el-container>
        <el-container class = "最后展示页面"  style="width:100%;margin-top: 10px;display: flex;flex-direction: column" v-show="OpenCourseTableIsV">
            <el-container class = "表格上方" style="margin-top: 10px;display: flex;flex-direction: row;">
                <span style="font-size: 20px;font-weight: bolder;">{{openCouSemester}}--开课列表</span>
            </el-container>
            <span style="font-size: 20px;font-weight: bolder; margin-left: 300px;color:red" v-show = "ifCanOpen" >请先开始上一个学期！</span>
            <el-container class = "最终开课列表" style="margin-top:15px;width:100%">
                <el-table
                    :data="openCourseList.filter(data => !openCourseSearch ||
                          data.courseId.toLowerCase().includes(openCourseSearch.toLowerCase()) ||
                          data.courseName.toLowerCase().includes(openCourseSearch.toLowerCase()) ||
                          data.staffId.toLowerCase().includes(openCourseSearch.toLowerCase())||
                          data.teachername.toLowerCase().includes(openCourseSearch.toLowerCase()))"
                    style="width: 100%"
                    stripe
                    max-height="440px">
                    <el-table-column fixed="left"
                                     prop="courseId" label="课程号" width="150" >
                    </el-table-column>
                    <el-table-column
                        prop="courseName" label="课程名称" width="150">
                    </el-table-column>
                    <el-table-column
                        prop="staffId" label="工号" width="100">
                    </el-table-column>
                    <el-table-column
                        prop="teachername" label="教师姓名" width="100">
                    </el-table-column>
                    <el-table-column
                        prop="classTime" label="上课时间" width="200">
                    </el-table-column>
                    <el-table-column
                        prop="volume" label="班级容量" width="150">
                    </el-table-column>
                    <el-table-column
                        prop="remnant" label="班级剩余容量" width="150">
                    </el-table-column>
                    <el-table-column  width = "220">
                        <template slot="header" slot-scope="{}">
                            <el-input
                                v-model="openCourseSearch"
                                size="mini"
                                placeholder="输入关键字搜索"/>
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
            dialogSeleTea:false,
            dialogOpenCou:false,
            formLabelWidth:'80px',

            //大页面可视
            isStepV:false,
            ifCanOpen:false,
            selCourseIsV:false,
            selTeacherIsV:false,
            setCouPeoIsV:false,
            OpenCourseTableIsV:false,
            message:['分配教师','设置班级情况','成功提交！学期状态改变'],
            //第一部分：选择开课列表
            openCouSemester:'',
            maxOptionDept:1,
            optionDept:[], //后端导入院系表，获得院系名字
            courseList:[],
            courseSelect:[],
            courseBackSele:[],
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
            teacherList:[],//需要放在表单里展示，
            currentCourse:null,
            teacherSearch:'',
            teacherSelect:[],
            teacherDeside:[],

            //第三部分：对最后的开课表进行更新
            openCourseList:[],
            currentOpenCourse:null,
            openCourseSearch:'',
            openCouForm:{
                semester:this.openCouSemester,
                courseId:'',
                staffId:'',
                staffName:'',
                classTime: '',
                volume:0,
                remnant:0,
            },
            canUpdate:true,
            canSubmit:false,
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
                console.log("当前学期");
                this.openCouSemester = res.data.find(item=>item.status === 0)?.semester;
                //当暂无学期状态为0的时候，选择学期状态为1的，展示开课表
                //加一个判断，如果状态又有1，又有0，则让管理员先去将那个状态为1的学期转化为2；只显示最后一个页面
                this.openCouSemester1 = res.data.find(item=>item.status === 1)?.semester;
                if(this.openCouSemester1!==undefined && this.openCouSemester !==undefined ){
                    this.OpenCourseTableIsV = true;
                    this.ifCanOpen=true;
                }
                else{
                    if(this.openCouSemester === undefined){
                        this.openCouSemester = res.data.find(item=>item.status === 1).semester;
                        if(typeof this.openCouSemester === 'undefined'){
                            this.selCourseIsV = false;
                        }else{
                            this.selCourseIsV = false;
                            this.isStepV = false;
                            this.OpenCourseTableIsV = true;
                            axios.get("/opencourse/getNowSemCourse?semester="+this.openCouSemester).then(res=>res.data).then(res=>{
                                console.log("当前学期开课数据",res.data);
                                this.openCourseList = res.data;
                            })
                        }
                    }
                    else{
                        this.isStepV = true;
                        this.selCourseIsV = true;
                    }
                }

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
        handleSelectionChangeLeft(val){
            //找到所有选择的行的索引
            this.courseSelect = val;
            console.log("加入排课的课程",this.courseSelect)
        },
        handleSelectionChangeRight(val){
            //找到所有选择的行的索引
            this.courseBackSele = val;
            console.log("加入排课的课程",this.courseSelect);
        },
        handleSelectionChangeTea(val){
            this.teacherSelect = val;
            console.log("加入选择的老师",this.teacherSelect);
        },

        //第三页面，对每一个此学期开课表中的课程填入班级容量，并进行classTime的最终修改。
        openCouEdit(row){
            console.log("编辑当前开课",row);
            this.currentOpenCourse = row;
            this.openCouForm.courseId = this.currentOpenCourse.courseId,
            this.openCouForm.courseName = this.currentOpenCourse.courseName,
            this.openCouForm.staffId = this.currentOpenCourse.staffId,
            this.openCouForm.staffName = this.currentOpenCourse.teachername,
            this.openCouForm.classTime = this.currentOpenCourse.classTime,
            this.openCouForm.volume = this.currentOpenCourse.volume,
            this.dialogOpenCou = true;
        },
        parseClassTime(classTime) {
            // 解析上课时间，返回[day, start, end]数组
            let day = this.parseDay(classTime);
            let [start, end] = classTime.slice(2).split("-");
            return [day, parseInt(start), parseInt(end)];
        },
        parseDay(classTime) {
            // 解析上课时间中的星期几，返回0-6表示周一至周日
            let dayStr = classTime.match(/周(.)\d+-\d+/)[1];
            let dayMap = new Map([
                ["一", 1],
                ["二", 2],
                ["三", 3],
                ["四", 4],
                ["五", 5],
                ["六", 6],
                ["日", 7],
            ]);
            return dayMap.get(dayStr);
        },
        confirmOpenCouEdit(){
            //在提交前选课时间前，先查看是否有 在同一个学期下，开了不同课程的老师，他的时间有无冲突。
            //写个ifelse判断，如果没问题，才运行下面的更新代码；否则提醒重新写时间
            //从数据库中找所有同一个学期的同一个老师的所有课程
            let newclassTime = this.openCouForm.classTime;
            let newList =newclassTime.split(",");//解析出新的上课时间
            console.log(newList)
            axios.get("/opencourse/getTeaClassTime?semester="+this.openCouSemester+"&staffId="+this.openCouForm.staffId+
            "&courseId="+this.openCouForm.courseId).then(res=>res.data).then(res=>{
                console.log("此老师所教的所有课程的课程时间",res.data);
                console.log(newclassTime)
                //对新插入的时间与数据库中进行比较
                for(const oldItem of res.data){
                    let oldclassTime = oldItem.classtime;
                    if(oldclassTime === "暂无")
                    {
                        continue;
                    }
                    let oldList = oldclassTime.split(",");
                    console.log("旧上课时间",oldList);
                    for(const i of newList){
                        for(const j of oldList)
                        {
                            let [day0, start0, end0] = this.parseClassTime(i); // 解析上课时间
                            let [day1, start1, end1] = this.parseClassTime(j);
                            if(day0==day1)
                            {
                                if((start0<=start1&&start1<=end0) ||(start1<=start0&&start0<=end1))
                                {
                                    this.canUpdate = false;
                                }
                            }
                        }
                    }
                }
                if(this.canUpdate){
                    axios.post("/opencourse/updateOpenCou",{
                        param:{
                            semester:this.openCouSemester,
                            courseId:this.openCouForm.courseId,
                            staffId:this.openCouForm.staffId,
                            classTime:this.openCouForm.classTime,
                            volume:this.openCouForm.volume,
                            remnant:this.openCouForm.volume, //这边没写错，一开始就是班级容量和剩余容量是一样的
                        }
                    }).then(res=>res.data).then(res=>{
                        if(res.code == "200"){
                            console.log(res);
                            if(res.code == "200"){
                                this.$message({
                                    type: 'success',
                                    message: `更新成功！`,
                                });
                                //更新成功后需要刷新一下
                                axios.get("/opencourse/getNowSemCourse?semester="+this.openCouSemester).then(res=>res.data).then(res=>{
                                    console.log("当前学期开课数据",res.data);
                                    this.openCourseList = res.data;
                                })
                                this.dialogOpenCou=false;
                            }
                            else{
                                this.$message({
                                    type: 'danger',
                                    message: `更新失败！`,

                                });
                            }
                        }
                    })
                }
                else{
                    this.$message({
                        type: 'error',
                        message: `开课失败！同一个老师开课时间冲突！`
                    })
                    this.canUpdate = true;
                }
            })



        },
        submit() {
            //提交，提交后无法再修改了
            for(const item of this.openCourseList){
                if(item.classTime === "暂无" || item.volume ===0 || item.remnant ===0 ){
                    this.canSubmit = false;
                    break;
                }
                else{
                    this.canSubmit = true;
                }
            }
            if(this.canSubmit){
                this.isStepV = false;
                this.selCourseIsV = false;
                this.selTeacherIsV = false;
                this.setCouPeoIsV = false;
                this.OpenCourseTableIsV = true;
                //提交成功后需要刷新一下，为最后的课程开课展示做准备
                axios.get("/opencourse/getNowSemCourse?semester="+this.openCouSemester).then(res=>res.data).then(res=>{
                    console.log("当前学期开课数据",res.data);
                    this.openCourseList = res.data;
                })
                //修改学期状态
                var status = 1;//学生可选课了
                axios.get("/semestatus/setStatus?semester="+this.openCouSemester+"&status="+status).then(res=>res.data).then(res=>{
                    if(res.code == "200"){
                        console.log("学期状态已修改");
                    }
                })
            }
            else{
                this.$alert('有开课信息未补充完整', '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$message({
                            type: 'info',
                            message: `返回: ${ action }`
                        });
                    }
                });
            }
        },
        //第二页面，选择老师，预填入classTime为暂无，因为classTime也是主键###################################################
        selectTea(row){
            console.log("派遣老师",row);
            this.currentCourse = row;
            //在这个时候对对应的教师表进行后端请求，根据课的学院来进行匹配
            axios.get("/teacher/getTeaDepList?deptName="+row.deptName).then(res=>res.data).then(res=>{
                console.log("当前的课程可指派老师",res.data)
                this.teacherList = res.data;
            })
            this.dialogSeleTea = true;
        },
        teacherAdd(){
            //添加老师进开课表，开课表正式插入数据
            //老师添加数组不共享，每个课程独立，所以只能选完就清空覆盖
            this.teacherDeside =this.teacherSelect;
            this.teacherList = this.teacherList.filter(teacher=>!this.teacherSelect.includes(teacher));
            console.log("选择的老师",this.teacherDeside);

        },
        confirmAddTea(){
            console.log(this.teacherDeside)
            for(const item of this.teacherDeside){
                console.log(item.staffid);
                //把加入当前课程的老师的信息与此课号还有当前的学期一同插入开课表中
                axios.post("/opencourse/insertNewCourse",{
                    param: {
                        semester: this.openCouSemester,
                        courseId: this.currentCourse.courseId,
                        staffId: item.staffid,
                        classTime: "暂无"
                    }
                }).then(res=>res.data).then(res=>{
                    if(res.code == "200")
                    {
                        console.log("添加成功")
                        this.$message({
                            type: 'success',
                            message: `添加成功！`,
                        });

                        this.courseOpen = this.courseOpen.filter(course=>course!==this.currentCourse);
                    }
                    else{
                        this.$message({
                            type: 'danger',
                            message: `添加失败！`,

                        });
                    }

                })

            }
            this.dialogSeleTea = false;//关闭选择teacher的框

        },
        //###########################################################################################################
        //第一页面，选择课程开课 ########################################################################################
        courseBack(){
            this.courseOpen = this.courseOpen.filter(course=>!this.courseBackSele.includes(course));
            this.courseList = [...this.courseBackSele,...this.courseList];
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
        //###########################################################################################################
        next() {
            if(this.courseOpen.length!==0&&this.step.active == 1){
                this.$alert('有课程仍未分配老师', '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$message({
                            type: 'info',
                            message: `返回: ${ action }`
                        });
                    }
                });
            }
            else {
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
                    if (this.step.active == 1) {
                        this.selCourseIsV = false;
                        this.setCouPeoIsV = false;
                        this.selTeacherIsV = true;
                    }//等于2的时候说明已经选择好老师了
                    else if (this.step.active == 2) {
                        this.selCourseIsV = false;
                        this.selTeacherIsV = false;
                        this.setCouPeoIsV = true;
                        //请求当前开课表数据
                        axios.get("/opencourse/getNowSemCourse?semester=" + this.openCouSemester).then(res => res.data).then(res => {
                            console.log("当前学期开课数据", res.data);
                            this.openCourseList = res.data;
                        })
                        this.step.submit = true;
                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消'
                    });
                });
            }

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