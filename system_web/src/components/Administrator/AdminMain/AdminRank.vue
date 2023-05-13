<template>
    <el-container class="整个部分" style="margin-top: 10px;display: flex;flex-direction: column">
        <el-container class="查找框选部分" style="margin-top: 10px;display:flex;flex-direction: row">
            <el-select v-model="input.selectSemester" placeholder="请选择学期" style="margin-right:10px;margin-top:10px;width:30%">
                <el-option v-for="item in optionSemester"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
            </el-select>
            <el-radio-group v-model="selectionOfSearch" style="margin-top: 10px">
                <el-radio-button label="dept">按照院系</el-radio-button>
                <el-radio-button label="course">按照课程</el-radio-button>
            </el-radio-group>
        </el-container>
        <el-container class="二选一" style="margin-top: 10px;width:30%">
            <el-form v-if="selectionOfSearch === 'dept'">
                <el-form-item label="选择院系">
                    <el-select v-model="input.selectDept" placeholder="请选择">
                        <el-option v-for="item in optionDept"
                                   :key="item.value"
                                   :label="item.label"
                                   :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <el-form v-else-if="selectionOfSearch === 'course'">
                <el-form-item label="搜索课程">
                    <el-input v-model="input.courseId" placeholder="请输入课程号"></el-input>
                    <el-input v-model="input.courseName" placeholder="请输入课程名称" style="margin-top: 5px"></el-input>
                </el-form-item>
            </el-form>
        </el-container>
        <el-container class="查找按钮" style="margin-top: 5px;display: flex;flex-direction: row">
            <el-button @click="searchClick()" type="primary" icon="el-icon-search" style="margin-right: 10px">搜索</el-button>
        </el-container>
        <el-container class="展示排名部分" style="margin-top: 15px">
            <el-table ref = "RankInfo" :data="FromDbInfo">
                <el-table-column fixed="left"
                                 prop = "rank" label = "排名" width = "150">
                </el-table-column>
                <el-table-column
                    prop="studentId" label="学号" width="130">
                </el-table-column>
                <el-table-column
                    prop="studentName" label="学生姓名" width="120">
                </el-table-column>
                <el-table-column
                    prop = "semester" label = "学期" width = "150">
                </el-table-column>
                <el-table-column
                    prop="deptName" label="院系" width="150" v-if="deptShow">
                </el-table-column>
                <el-table-column
                    prop="courseId" label="课程号" width="150" v-if="courseShow">
                </el-table-column>
                <el-table-column
                    prop="courseName" label="课程名字" width="150" v-if="courseShow">
                </el-table-column>
                <el-table-column
                    prop="finalScore" label="综合成绩" width="150" v-if="courseShow">
                </el-table-column>
                <el-table-column
                    prop="scorePoint" label="绩点" width="150">
                </el-table-column>
            </el-table>

        </el-container>
        <el-pagination
            @current-change="handleCurrentChange"
            @size-change="handleSizeChange"
            :current-page="pagination.pagenum"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="pagination.pagesize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pagination.total"
        />
    </el-container>

</template>

<script>
import axios from "axios";


export default {
    name: "AdminRank",
    data(){
      return{
          maxOptionSemester:1,
          maxOptionDept:1,
          optionSemester:[], //后端导入学期表，获得学期
          optionDept:[], //后端导入院系表，获得院系名字
          selectionOfSearch:'',
          input:{
              selectSemester:"",
              courseId:"",
              courseName:"",
              studentId: "",
              studentName: "",
              selectDept:"",
          },
          courseShow : false,
          deptShow:false,
          FromDbInfo:[],
          pagination:{
              pagenum:1,
              pagesize:10,
              totalpage:0,
              total:0
          },

      }
    },
    methods:{
        loadData(){
            this.maxOptionSemester = 1;
            this.optionSemester = [];
            this.maxOptionDept = 1;
            this.optionDept = [];
            console.log("loadsomething")
            //获取学期信息
            axios.get("/semestatus/list").then(res=>{
                console.log("获取学期成功",res.data);    //获取成功
                for(const item of res.data)
                {
                    if(item.status!==3)       //未能结课的学期不放进列表中
                    {
                        continue;
                    }
                    let newDict={};
                    newDict['value'] = '选项'+this.maxOptionSemester;
                    newDict['label'] = item.semester;
                    this.optionSemester.push(newDict);
                    this.maxOptionSemester+=1;
                }
                console.log('学期选择框',this.optionSemester);
            })
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
        ScoreTrans(finalScore){
            switch (true) {
                case finalScore>= 90:
                    return 4.0
                case finalScore>= 86:
                    return 3.7
                case finalScore>= 83:
                    return 3.3
                case finalScore>= 80:
                    return 3.0
                case finalScore>=76:
                    return 2.7
                case finalScore>=73:
                    return 2.3
                case finalScore>=70:
                    return 2.0
                case finalScore>=66:
                    return 1.7
                case finalScore>=63:
                    return 1.3
                case finalScore>=60:
                    return 1.0
                default:
                    return 0.0
            }
        },
        rankTrans(fromDbInfo){
            var i = 0;
            for(const item of fromDbInfo){
                item.rank = (this.pagination.pagenum-1)*this.pagination.pagesize+1;
                item.rank +=i;
                i++;
            }
        },
        handleCurrentChange(currentPage) {
            //分页有问题：为什么有问题呢？
            //是因为数据库也分页，然后它传来前端的时候，总数据只有一页的长度，所以totalpage会出问题
            // 修改当前页码，并重新查询数据
            this.pagination.pagenum = currentPage;
            if(this.input.selectSemester==='')
            {
                this.loadData();
            }
            else{
                this.searchClick();
            }

        },
        handleSizeChange(pageSize){
            this.pagination.pagesize = pageSize;
            console.log("换页面大小",this.input.selectSemester)
            if(this.input.selectSemester==='')
            {
                this.loadData();
            }
            else{
                this.searchClick();
            }
        },

        loadCourseRank(semester){
            if(this.input.courseId===''&&this.input.courseName===''){
                this.$alert('请选择学期和排名规则！', '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$message({
                            type: 'info',
                            message: `信息: ${ action }`
                        });
                    }
                });
            }
            this.input.selectDept = "";
            this.deptShow = false;
            this.input.selectDept;
            console.log("查看getPagebug",this.input.courseId,this.input.courseName)
            axios.post("/selectcourse/getRank",{
                param:{
                    semester:semester,
                    courseId:this.input.courseId,
                    courseName:this.input.courseName,
                    deptName:this.input.selectDept,
                    isPage:"不需要"
                }
            }).then(res=>res.data).then(res=>{
                console.log("getPage是否成功",res.total);
                this.pagination.totalpage = Math.ceil(res.total/this.pagination.pagesize);
                this.pagination.total = res.total;
                axios.post("/selectcourse/getRank",{
                    pagesize:this.pagination.pagesize,
                    pagenum:this.pagination.pagenum,
                    param:{
                        semester:semester,
                        courseId:this.input.courseId,
                        courseName:this.input.courseName,
                        deptName:this.input.selectDept,
                        isPage:"需要"
                    }
                }).then(res=>res.data).then(res=>{
                    if(res.code=="200"){
                        console.log("搜索排名",res.data);
                        for(const item of res.data){
                            item.scorePoint = this.ScoreTrans(item.finalScore);
                        }
                        this.rankTrans(res.data);
                        this.FromDbInfo = res.data;
                        this.courseShow = true;
                        if(this.FromDbInfo.length ===0){
                            this.$message({
                                type:'info',
                                message:`暂无数据`,
                            });
                        }
                        else{
                            this.$message({
                                type:'success',
                                message:`查找成功`,
                            });

                        }
                    }

                })
            })
        },
        loadDeptRank(semester){
            if(this.input.selectDept===''){
                this.$alert('请选择学期和排名规则！', '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$message({
                            type: 'info',
                            message: `信息: ${ action }`
                        });
                    }
                });
            }
            this.courseShow = false;
            this.input.courseName = "";
            this.input.courseId = "";
            var dept_dict = this.optionDept.find(x=>x.value===this.input.selectDept);
            var deptName = dept_dict.label;
            console.log("按照院系来排名");
            axios.post("/selectcourse/getRank",{
                param:{
                    semester:semester,
                    courseId:this.input.courseId,
                    courseName:this.input.courseName,
                    deptName:deptName,
                    isPage:"不需要"
                },

            }).then(res=>res.data).then(res=>{
                console.log("获得信息总数",res.total);
                this.pagination.totalpage = Math.ceil(res.total/this.pagination.pagesize);
                this.pagination.total = res.total
                axios.post("/selectcourse/getRank",{
                    pagesize:this.pagination.pagesize,
                    pagenum:this.pagination.pagenum,
                    param:{
                        semester:semester,
                        courseId:this.input.courseId,
                        courseName:this.input.courseName,
                        deptName:deptName,
                        isPage:"需要"
                    }
                }).then(res=>res.data).then(res=>{
                    if(res.code=="200"){
                        console.log("搜索排名",res.data);
                        for(const item of res.data){
                            item.scorePoint = this.ScoreTrans(item.semeFinalScore);
                        }
                        this.rankTrans(res.data);
                        this.FromDbInfo = res.data;
                        this.deptShow = true;
                        if(this.FromDbInfo.length ===0){
                            this.$message({
                                type:'info',
                                message:`暂无数据`,
                            });
                        }
                        else{
                            this.$message({
                                type:'success',
                                message:`查找成功`,
                            });

                        }
                    }

                })
            })
        },

        searchClick(){
            //搜之前做判断，如果学期没选，就不做搜索，提醒用户必须选择学期
            console.log("搜索",this.input.selectSemester)
            if(this.input.selectSemester===''||this.selectionOfSearch===''){
                this.$alert('请选择学期和排名规则！', '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$message({
                            type: 'info',
                            message: `信息: ${ action }`
                        });
                    }
                });
            }
            //解析所选的学期
            var semester_dict = this.optionSemester.find(x=>x.value===this.input.selectSemester);
            var semester = semester_dict.label;
            if(this.selectionOfSearch ==='course'){
                console.log(this.selectionOfSearch)
                this.loadCourseRank(semester);
            }
            else if(this.selectionOfSearch === 'dept'){
                console.log(this.selectionOfSearch)
                this.loadDeptRank(semester);
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