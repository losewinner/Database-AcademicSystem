<template>
<el-container style="display: flex;flex-direction: column">
    <el-container class="查找框集合" style="display: flex;flex-direction: column">
        <el-container class="学期，课程号，课程名字" style="margin-top: 20px">
            <el-input placeholder="请选择学期" suffix-icon="el-icon-date" v-model="input.semester" style="width:30%;margin-right: 5px"></el-input>
            <el-input placeholder="请输入课程号" suffix-icon="el-icon-s-management" v-model="input.courseId" style="width:30% ;margin-right: 5px"></el-input>
            <el-input placeholder="请输入课程名字" suffix-icon="el-icon-s-management" v-model="input.courseName" style="width:30% ;margin-right: 5px"></el-input>
        </el-container>
        <el-container class="学生号，学生姓名" style="margin-top: 20px">
            <el-input placeholder="请输入学生号" suffix-icon="el-icon-user" v-model="input.studentId" style="width:30%;margin-right: 5px"></el-input>
            <el-input placeholder="请输入学生姓名" suffix-icon="el-icon-user" v-model="input.studentName" style="width:30% ;margin-right: 5px"></el-input>
        </el-container>
        <el-container class="教师号，教师姓名" style="margin-top: 20px">
            <el-input placeholder="请输入教师号" suffix-icon="el-icon-s-custom" v-model="input.staffId" style="width:30%;margin-right: 5px"></el-input>
            <el-input placeholder="请输入教师姓名" suffix-icon="el-icon-s-custom" v-model="input.staffName" style="width:30% ;margin-right: 5px"></el-input>
        </el-container>
    </el-container>
    <el-container class="查找结果表格" style="margin-top: 20px">
        <div>表格</div>
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
        }
    }
}
</script>

<style scoped>

</style>