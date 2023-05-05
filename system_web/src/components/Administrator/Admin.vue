<template>
    <el-descriptions class="margin-top" title="个人信息" :column="3" :size="'medium'" border style="margin-top: 20px">
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                职工号
            </template>
            {{ Admininfo.staffid }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                姓名
            </template>
            {{ Admininfo.teachername }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                身份
            </template>
            {{ Admininfo.title }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                性别
            </template>
            {{ Admininfo.sex }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                生日
            </template>
            {{ Admininfo.birth }}
        </el-descriptions-item>
    </el-descriptions>
</template>

<script>

export default {
    name:'Admin',
    data(){
        return{
            Admininfo:[],
        }
    },
    created() {
        console.log("home loading")
        this.Admininfo = JSON.parse(sessionStorage.getItem("curuser"))
    },
}
</script>

<style scoped>

</style>