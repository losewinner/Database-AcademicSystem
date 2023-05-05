<template>
<el-descriptions class="margin-top" title="个人信息" :column="3" :size="'medium'" border>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        职工号
      </template>
      {{ teainfo.staffid }}
    </el-descriptions-item>
  <el-descriptions-item>
    <template slot="label">
      <i class="el-icon-user"></i>
      姓名
    </template>
    {{ teainfo.teachername }}
  </el-descriptions-item>
  <el-descriptions-item>
    <template slot="label">
      <i class="el-icon-user"></i>
      职称
    </template>
    {{ teainfo.title }}
  </el-descriptions-item>
  <el-descriptions-item>
    <template slot="label">
      <i class="el-icon-user"></i>
      性别
    </template>
    {{ teainfo.sex }}
  </el-descriptions-item>
  <el-descriptions-item>
    <template slot="label">
      <i class="el-icon-user"></i>
      生日
    </template>
    {{ teainfo.birth }}
  </el-descriptions-item>
</el-descriptions>
</template>
<script>
export default {
  name: "Teacher",
  data(){
    return{
      teainfo:[]
    }
  },
  created() {
    console.log("home loading")
    this.teainfo = JSON.parse(sessionStorage.getItem("curuser"))
  },
  methods:{

  }
}
</script>

<style scoped>

</style>