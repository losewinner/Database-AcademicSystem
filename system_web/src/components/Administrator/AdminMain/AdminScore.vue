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
            <el-radio-group v-model="selectionOfSearch" style="margin-top: 10px">
                <el-radio-button label="student">按照学生</el-radio-button>
                <el-radio-button label="course">按照课程</el-radio-button>
            </el-radio-group>
            <el-container class="二选一" style="margin-top: 10px">
                <el-form v-if="selectionOfSearch === 'student'">
                    <el-form-item label ="搜索学生">
                        <el-input placeholder="请输入学生号" suffix-icon="el-icon-user" v-model="input.studentId" style="width:40%;margin-right: 5px"></el-input>
                        <el-input placeholder="请输入学生姓名" suffix-icon="el-icon-user" v-model="input.studentName" style="width:40% ;margin-right: 5px"></el-input>
                    </el-form-item>
                </el-form>
                <el-form v-else-if="selectionOfSearch === 'course'">
                    <el-form-item label="搜索课程">
                        <el-input placeholder="请输入课程号" suffix-icon="el-icon-s-management" v-model="input.courseId" style="width:40% ;margin-right: 5px"></el-input>
                        <el-input placeholder="请输入课程名字" suffix-icon="el-icon-s-management" v-model="input.courseName" style="width:40% ;margin-right: 5px"></el-input>
                    </el-form-item>
                </el-form>
            </el-container>
            <el-container class="查找按钮" style="margin-top: 5px">
                <el-button @click="searchClick()" type="primary" icon="el-icon-search">搜索</el-button>
            </el-container>
        </el-container>
        <el-container class="统计分数结果（平均分，最高分，最低分）" style = "margin-top: 20px">
            <el-form v-if="selectionOfSearch ==='course'">
                <el-descriptions :column="3" :size="medium" border>
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
            </el-form>
        </el-container>
    </el-container>

    <el-container class="查找结果表格" style="margin-top: 20px">
        <el-table
            :data="FromDbInfo"
            style="width: 70%"
            max-height="250">
            <el-table-column fixed="left"
                             prop="semester" label="学期" width="150">
            </el-table-column>
            <el-table-column
                prop="studentId" label="学号" width="130">
            </el-table-column>
            <el-table-column
                prop="studentName" label="学生姓名" width="120">
            </el-table-column>
            <el-table-column
                prop="courseId" label="课程号" width="150">
            </el-table-column>
            <el-table-column
                prop="courseName" label="课程名字" width="150">
            </el-table-column>
            <el-table-column
                prop="score" label="绩点" width="100">
            </el-table-column>
            <el-table-column
                fixed="right" label="操作" width="170">
                <template slot-scope="scope">
                    <el-button  @click="editClick(scope.row)" type="text" size="medium">编辑</el-button>
                    <el-button
                        @click.native.prevent="deleteRow(scope.$index, FromDbInfo)"
                        type="text"
                        size="medium" style="color:red">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-container>

</el-container>

</template>


<script>
import axios from "axios"
export default {
    name: "AdminScore",
    data(){
        //如果data里面什么都不写，页签跳转会出问题！
        return{
            optionSemester:[{value:'选项1',label:'2023春季'}], //后端导入学期表，获得学期
            selectionOfSearch:'',
            maxOptionValue:1,
            input:{        //前端输入，通过前端此字典绑定，然后去后端查找
            courseId:'',
            courseName:'',
            studentId:'',
            studentName:'',
            selectSemester:'',
            },
            FromDbInfo:[{         //后端通过前端的输入查找到的信息放在这个字典数组中，
                semester: '2023春季',
                studentId: '12345',
                studentName:'陆靖宇',
                courseId: '12345',
                courseName:'BB88',
                score: 2003
            }],
            ScoreAnalysis:{      //后端数据库制作三种分的视图，将三分记下来，放进这个字典中。
                averageScore:50,
                highestScore:100,
                lowestScore:0.
            }
        }
    },
    methods:{
        deleteRow(index,rows){     //不仅前端移除，且数据库选课表中也要把这个学生的元组删去。
            rows.splice(index,1);
        },
        editClick(row){
            console.log(row)
        },
        searchClick(){
            //搜之前做判断，如果学期没选，就不做搜索，提醒用户必须选择学期
            console.log("wtf")
            if(this.selectSemester===''){
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

        },
        loadData(){
            //向数据库请求数据，涉及：学期表，学生表，选课表，教师表
            console.log("w");
            //获取学期信息，放进optionSemester中
            //先尝试只获取学期表，
            axios.get("http://127.0.0.1:8080/semestatus/list").then(res=>{
                console.log("yeyeye",res.data);    //获取成功
                for(const item of res.data)
                {
                    if(item.status===0)       //未能结课的学期不放进列表中
                    {
                        continue;
                    }
                    let newDict={};
                    newDict['value'] = '选项'+(this.maxOptionValue+1);
                    newDict['label'] = item.semester;
                    this.optionSemester.push(newDict);
                    this.maxOptionValue+=1;
                }
                console.log('wwwwwww',this.optionSemester);
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