<template>
    <el-descriptions class="margin-top" title="个人信息" :column="4" :size="'medium'" border>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                学号
            </template>
            {{ stuinfo.studentid }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                姓名
            </template>
            {{ stuinfo.name }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                性别
            </template>
            {{ stuinfo.sex }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                学院号
            </template>
            {{ stuinfo.deptid }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                居住地
            </template>
            {{ stuinfo.home }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                电话
            </template>
            {{ stuinfo.phone }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                生日
            </template>
            {{ stuinfo.birth }}
        </el-descriptions-item>
    </el-descriptions>
</template>

<script>

export default {
  name:'Student',
    data(){
        return{
            stuinfo:[]
        }
    },
    created() {
        console.log("home loading")
        this.stuinfo = JSON.parse(sessionStorage.getItem("curuser"))
        localStorage.setItem("userid",this.stuinfo.studentid);
        console.log("??",localStorage.getItem("userid"));
    },
}
</script>

<style scoped>

</style>