<template>
<el-container style="display: flex;flex-direction: column;margin-top:15px">
    <el-container class="上半部分" style="display: flex;flex-direction: row">
        <el-container class="查找框集合" style="display: flex;flex-direction: column ; margin-right:100px ;width:40%">
            <el-select v-model="input.selectSemester" placeholder="请选择学期" style="width:30%">
                <el-option v-for="item in optionSemester"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
            </el-select>
            <el-container class="课程，课程号" style="margin-top: 20px;display: flex;flex-direction: row">
                <el-input placeholder="请输入课程号" suffix-icon="el-icon-s-management" v-model="input.courseId" style="width:40% ;margin-right: 5px"></el-input>
                <el-input placeholder="请输入课程名字" suffix-icon="el-icon-s-management" v-model="input.courseName" style="width:40% ;margin-right: 5px"></el-input>
            </el-container>
            <el-container class="学号，学生名" style="margin-top: 20px;display: flex;flex-direction: row">
                <el-input placeholder="请输入学生号" suffix-icon="el-icon-user" v-model="input.studentId" style="width:40%;margin-right: 5px"></el-input>
                <el-input placeholder="请输入学生姓名" suffix-icon="el-icon-user" v-model="input.studentName" style="width:40% ;margin-right: 5px"></el-input>
            </el-container>

            <el-container class="查找，删除按钮" style="margin-top: 5px;display: flex;flex-direction: row">
                <el-button @click="searchClick()" type="primary" icon="el-icon-search" style="margin-right: 10px">搜索</el-button>
                <el-popconfirm
                    confirm-button-text='确认'
                    cancel-button-text='取消'
                    icon="el-icon-info"
                    icon-color="red"
                    title="是否删除选中数据？"
                    @confirm="deleteSelect()">
                    <el-button slot="reference" icon = "el-icon-delete" type="danger">删除</el-button>
                </el-popconfirm>
            </el-container>
        </el-container>
        <el-container class="统计分数结果（平均分，最高分，最低分）" style = "margin-top: 20px">
            <el-descriptions :column="4"  border>
                <el-descriptions-item>
                    <template slot="label">课程</template>
                    {{ScoreAnalysis.courseName}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">平均分</template>
                    {{ScoreAnalysis.averageScore}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">最高分</template>
                    {{ScoreAnalysis.highestScore}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">最低分</template>
                    {{ScoreAnalysis.lowestScore}}
                </el-descriptions-item>
            </el-descriptions>
        </el-container>
    </el-container>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pagenum"
        :page-sizes="[10, 15, 25, 30]"
        :page-size="pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalpage" style="margin-top: 10px;margin-bottom: 10px">
    </el-pagination>

    <el-container class="查找结果表格" style="margin-top: 20px">

        <el-table
            ref = "ScoreInfo"
            :data="FromDbInfo"
            style="width: 70%"
            max-height="auto"
            @selection-change="handleSelectionChange">
            <el-table-column
                type="selection"
                width="55">
            </el-table-column>
            <el-table-column fixed="left"
                             prop="semester" label="学期" width="150" sortable>
            </el-table-column>
            <el-table-column
                prop="studentId" label="学号" width="130" sortable>
            </el-table-column>
            <el-table-column
                prop="studentName" label="学生姓名" width="120">
            </el-table-column>
            <el-table-column
                prop="courseId" label="课程号" width="150" sortable>
            </el-table-column>
            <el-table-column
                prop="courseName" label="课程名字" width="150">
            </el-table-column>
            <el-table-column
                prop="score" label="平时成绩" width="150" sortable>
            </el-table-column>
            <el-table-column
                prop="testScore" label="考试成绩" width="150" sortable>
            </el-table-column>
            <el-table-column
                prop="finalScore" label="综合成绩" width="150" sortable>
            </el-table-column>
            <el-table-column
                prop="scorePoint" label="绩点" width="150" sortable>
            </el-table-column>
            <el-table-column
                fixed="right" label="操作" width="170">
                <template slot-scope="scope">
                    <el-button  @click="editClick(scope.row)" type="text" size="medium">编辑</el-button>

                </template>
            </el-table-column>
        </el-table>
    </el-container>
    <el-dialog title="修改成绩" :visible.sync="dialogFormVisible">
        <el-form :model="form">
            <el-form-item label="平时成绩" :label-width="formLabelWidth">
                <el-input v-model="form.score" ></el-input>
            </el-form-item>
            <el-form-item label="考试成绩" :label-width="formLabelWidth">
                <el-input v-model="form.testScore"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="confirmEdit">确 定</el-button>
        </div>
    </el-dialog>
</el-container>

</template>


<script>
import axios from "axios"
export default {
    name: "AdminScore",
    data(){
        //如果data里面什么都不写，页签跳转会出问题！
        return{
            optionSemester:[], //后端导入学期表，获得学期
            maxOptionValue:1,
            input:{        //前端输入，通过前端此字典绑定，然后去后端查找
                courseId:"",
                courseName:"",
                studentId:"",
                studentName:"",
                selectSemester:"",
            },
            FromDbInfo:[], //后端通过前端的输入查找到的信息放在这个字典数组中，
            ScoreAnalysis:{      //后端数据库制作三种分的视图，将三分记下来，放进这个字典中。
                courseName:"xxx",
                averageScore:"xxx",
                highestScore:"xxx",
                lowestScore:"xxx",
            },
            AllScore:[],    //当只选了课程的时候，存放一下此课程的所有最终成绩之和
            ToDbInfo:[],
            selectedRowIndexes:[],
            pagesize:10,
            pagenum:1,
            totalpage:0,
            dialogFormVisible: false,
            formLabelWidth: '120px',
            form:{
                score:null,
                testScore:null,
            },
            currentRow:null
        }
    },
    methods:{
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

        handleCurrentChange(currentPage) {
            //分页有问题：为什么有问题呢？
            //是因为数据库也分页，然后它传来前端的时候，总数据只有一页的长度，所以totalpage会出问题
            // 修改当前页码，并重新查询数据
            this.pagenum = currentPage;
            if(this.input.selectSemester==='')
            {
                this.loadData();
            }
            else{
                this.searchClick();
            }

        },
        handleSizeChange(pageSize){
            this.pagesize = pageSize;
            if(this.input.selectSemester==='')
            {
                this.loadData();
            }
            else{
                this.searchClick();
            }
        },
        deleteSelect(){     //不仅前端移除，且数据库选课表中也要把这个学生的元组删去。
            //通过勾选框的数据来进行删除
            console.log("删除操作",this.ToDbInfo);
            //删除前端页面上的数据，不需要刷新，但建议此操作在数据库操作后进行
            //因为数据库可能删除不成功
            axios.post("/selectcourse/deleteScore",{
                param: {
                    DeleteList: this.ToDbInfo
                }
            }).then(res=>res.data).then(res=>{
                if(res.code == "200"){
                    console.log("成功删除");
                    this.selectedRowIndexes.forEach(index => {
                        this.FromDbInfo.splice(index, 1); // 根据排序后的索引删除数组中的元素
                    });
                    this.searchClick();
                }
            });

        },
        handleSelectionChange(val){
            this.ToDbInfo = val;
            console.log("选择框",this.ToDbInfo);
            this.selectedRowIndexes = val.map(item => this.FromDbInfo.indexOf(item));
            this.selectedRowIndexes.sort((a,b)=>b-a)
            console.log(this.selectedRowIndexes);
        },
        editClick(row){
            //编辑成绩，只能编辑四个成绩
            console.log("修改成绩",row);
            this.dialogFormVisible = true;
            this.currentRow = row;
        },
        confirmEdit(){
            console.log(this.form.testScore,this.form.score);
            this.dialogFormVisible = false;
            console.log(this.currentRow.semester);
            //得到了成绩，把对应的学期学号工号课程号一起传回后端，让后端数据库重新算一遍
            axios.post("/selectcourse/editScore",{
                studentId:this.currentRow.studentId,
                semester:this.currentRow.semester,
                courseId:this.currentRow.courseId,
                staffId:this.currentRow.staffId,
                classTime:this.currentRow.classTime,
                score:this.form.score,
                testScore:this.form.testScore,
            }).then(res=>res.data).then(res=>{
                console.log(res);
                if(res.code == "200"){
                    this.$message({
                        type: 'success',
                        message: `更新成功！`,
                    });
                    //更新成功后需要刷新一下
                    if(this.input.selectSemester===''){
                        this.loadData();
                    }else{
                        this.searchClick();
                    }
                }
                else{
                    this.$message({
                        type: 'danger',
                        message: `更新失败！`,

                    });
                }
            })

        },
        searchClick(){
            //搜之前做判断，如果学期没选，就不做搜索，提醒用户必须选择学期
            console.log("wtf",this.input.selectSemester)
            if(this.input.selectSemester===''){
                this.$alert('请选择学期！', '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$message({
                            type: 'info',
                            message: `信息: ${ action }`
                        });
                    }
                });
            }
            //先获取input的东西（v-model双向绑定自动获取了
            console.log(this.input);
            //然后获取对应的数据
            //解析所选的学期
            var semester_dict = this.optionSemester.find(x=>x.value===this.input.selectSemester);
            var semester = semester_dict.label;
            //按照学生和课程的输入来选择
            axios.post("/selectcourse/getPage",{
                param:{
                    semester:semester,
                    studentId:this.input.studentId,
                    studentName:this.input.studentName,
                    courseId:this.input.courseId,
                    courseName:this.input.courseName
                }
            }).then(res=>res.data).then(res=>{
                this.totalpage = Math.ceil(res.total/this.pagesize);
                this.AllScore = res.data;
                console.log("所有成绩",this.AllScore);
                axios.post("/selectcourse/getScore",{
                    pagesize:this.pagesize,
                    pagenum:this.pagenum,
                    param:{
                        semester:semester,
                        studentId:this.input.studentId,
                        studentName: this.input.studentName,
                        courseId :this.input.courseId,
                        courseName: this.input.courseName
                    }
                }).then(res=>res.data).then(res=>{
                    if(res.code=="200"){
                    //换算成绩为绩点
                        for(const item of res.data){
                            item.scorePoint = this.ScoreTrans(item.finalScore);
                        }
                        this.FromDbInfo = res.data;
                        console.log("查找成绩",this.FromDbInfo);
                        if(this.FromDbInfo.length===0) {
                            this.$message({
                                type: 'info',
                                message: `暂无数据！`,
                            });
                        }
                        else {

                            this.$message({
                                type: 'success',
                                message: `查找成功！`,
                            });
                            if((this.input.courseId!==''||this.input.courseName!=='')
                                &&(this.input.studentId===''&&this.input.studentName==='')){
                                console.log("只搜索了课程");
                                this.ScoreAnalysis.courseName = this.FromDbInfo[0].courseName;
                                this.ScoreAnalysis.averageScore = this.AllScore.reduce((acc, val) => acc + val, 0) / this.AllScore.length;

                                this.ScoreAnalysis.highestScore = Math.max(...this.AllScore);

                                this.ScoreAnalysis.lowestScore = Math.min(...this.AllScore);
                                console.log("分数分析",this.ScoreAnalysis);
                            }
                            else if(this.input.studentId!==''||this.input.studentName!==''||((this.input.courseId===''&&this.input.courseName==='')))
                            {
                                this.ScoreAnalysis.courseName = "xxx";
                                this.ScoreAnalysis.lowestScore = "xxx";
                                this.ScoreAnalysis.averageScore = "xxx";
                                this.ScoreAnalysis.highestScore = "xxx";
                            }
                        }
                    }
                })
            })
        },
        loadData(){
            //向数据库请求数据，涉及：学期表
            this.maxOptionValue = 1;
            this.optionSemester = [];
            console.log("w");
            //获取学期信息，放进optionSemester中
            var that = this;
            axios.get("/semestatus/list").then(res=>{
                console.log("yeyeye",res.data);    //获取成功
                for(const item of res.data)
                {
                    if(item.status===0)       //未能结课的学期不放进列表中
                    {
                        //continue;
                    }
                    let newDict={};
                    newDict['value'] = '选项'+that.maxOptionValue;
                    newDict['label'] = item.semester;
                    that.optionSemester.push(newDict);
                    that.maxOptionValue+=1;
                }
                console.log('wwwwwww',that.optionSemester);
            })
            axios.post("/selectcourse/getPage",{
                param:{
                    semester: '',
                    studentId:'',
                    studentName:'',
                    courseId:'',
                    courseName:''
                }
            }).then(res=>res.data).then(res=>{
                console.log("页面大小",res.total);
                this.totalpage = Math.ceil(res.total/this.pagesize);
                axios.get("/selectcourse/getAllScore?pagenum="+this.pagenum+"&pagesize="+this.pagesize).then(res=>res.data).then(res=>{
                    if(res.code=="200"){
                        console.log(res.data);
                        //换算成绩为绩点
                        for(const item of res.data){
                            item.scorePoint = this.ScoreTrans(item.finalScore);
                        }
                        this.FromDbInfo = res.data;
                        if(this.FromDbInfo.length===0) {
                            this.$message({
                                type: 'info',
                                message: `暂无数据！`,

                            });
                        }
                    }
                })
            })


        }
    },
    created(){
        this.loadData();
    }
}
</script>

<style scoped>

</style>