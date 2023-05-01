<template>
<el-container style="display: flex;flex-direction: column">
    <el-container class="查找框集合" style="display: flex;flex-direction: column">
        <el-container class="学期，课程号，课程名字" style="margin-top: 20px">
            <el-input placeholder="请选择学期" suffix-icon="el-icon-date" v-model="input.semester" style="width:30%;margin-right: 5px"></el-input>
            <el-input placeholder="请输入课程号" suffix-icon="el-icon-s-management" v-model="input.courseId" style="width:30% ;margin-right: 5px"></el-input>
            <el-input placeholder="请输入课程名字" suffix-icon="el-icon-s-management" v-model="input.courseName" style="width:30% ;margin-right: 5px"></el-input>
        </el-container>
        <el-container class="学生号，学生姓名" style="margin-top: 10px">
            <el-input placeholder="请输入学生号" suffix-icon="el-icon-user" v-model="input.studentId" style="width:30%;margin-right: 5px"></el-input>
            <el-input placeholder="请输入学生姓名" suffix-icon="el-icon-user" v-model="input.studentName" style="width:30% ;margin-right: 5px"></el-input>
        </el-container>
        <el-container class="教师号，教师姓名" style="margin-top:10px">
            <el-input placeholder="请输入教师号" suffix-icon="el-icon-s-custom" v-model="input.staffId" style="width:30%;margin-right: 5px"></el-input>
            <el-input placeholder="请输入教师姓名" suffix-icon="el-icon-s-custom" v-model="input.staffName" style="width:30% ;margin-right: 5px"></el-input>
        </el-container>
        <el-container class="查找按钮" style="margin-top: 20px">
            <el-button @click="searchClick()" type="primary" icon="el-icon-search">搜索</el-button>
        </el-container>
    </el-container>
    <el-container class="查找结果表格" style="margin-top: 20px">
        <el-table
            :data="FromDbInfo"
            style="width: 70%"
            max-height="250">
            <el-table-column
                fixed
                prop="semester"
                label="学期"
                width="150">
            </el-table-column>
            <el-table-column
                prop="studentId"
                label="学号"
                width="150">
            </el-table-column>
            <el-table-column
                prop="studentName"
                label="学生姓名"
                width="150">
            </el-table-column>
            <el-table-column
                prop="staffId"
                label="工号"
                width="150">
            </el-table-column>
            <el-table-column
                prop="staffName"
                label="老师姓名"
                width="150">
            </el-table-column>
            <el-table-column
                prop="score"
                label="绩点"
                width="120">
            </el-table-column>
            <el-table-column
                fixed="right"
                label="操作"
                width="170">
                <template slot-scope="scope">
                    <el-button  @click="handleClick(scope.row)" type="text" size="medium">编辑</el-button>
                    <el-button
                        @click.native.prevent="deleteRow(scope.$index, FromDbInfo)"
                        type="text"
                        size="medium" style="color:red">
                        移除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-container>
    <el-container class="统计分数结果（平均分，最高分，最低分）" style = "margin-top: 20px">
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
    </el-container>
</el-container>

</template>

<script>
export default {
    name: "AdminScore",
    data(){
        //如果data里面什么都不写，页签跳转会出问题！
        return{
            input:{
            semester:'',
            courseId:'',
            courseName:'',
            studentId:'',
            studentName:'',
            staffId:'',
            staffName:''
            },
            FromDbInfo:[{
                semester: '2023春季',
                studentId: '12345',
                studentName:'陆靖宇',
                courseId: '12345',
                courseName:'BB88',
                staffId: '123',
                staffName:'lfy',
                score: 2003
            }],
            ScoreAnalysis:{
                averageScore:50,
                highestScore:100,
                lowestScore:0.
            }
        }
    },
    methods:{
        deleteRow(index,rows){
            rows.splice(index,1);
        },
        handleClick(row){
            console.log(row)
        },
        searchClick(){
            console.log("wtf")
        }
    }
}
</script>

<style scoped>

</style>