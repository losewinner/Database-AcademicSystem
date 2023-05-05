<template>
  <div>
    <el-table :data="data"
              style="width: 100%">
        <el-table-column prop="studentid" label="学号" width="120" sortable></el-table-column>
        <el-table-column prop="name" label="姓名" width="120" ></el-table-column>
        <el-table-column prop="signscore" label="平时成绩" width="120" sortable>

        </el-table-column>

        <el-table-column prop="testscore" label="考试成绩" width="120" sortable></el-table-column>
        <el-table-column prop="score" label="总成绩" width="120" sortable></el-table-column>
    </el-table>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "ModifyResults",
  data(){
    return{
      data:[],
      dataLen:0,

    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      console.log("loading")
      this.staffId=1



      this.semester=1
      this.courseId=1

      this.classTime=1
      axios.post("http://localhost:8080/selectcourse/getstudent" , {
        pagesize:3,
        pagenum:1,
        param:{
          "semester":1,
          "courseid":1,
          "staffid":1,
          "classtime":1
        }
      }).then(res=>{
        this.data=res.data.data
        this.dataLen = res.data.total
        console.log(this.data)
      })
      // axios.get("http://localhost:8080/selectcourse/allstudent?semester="+this.semester+"&courseId="+this.courseId+"&staffId="+
      // this.staffId+"&classTime="+this.classTime).then(response=>{
      //     const {
      //       data,
      //       status
      //     }=response;
      //     if(status === "200"){
      //       this.tableData = data.total;
      //       console.log(this.tableData)
      //     }
      //     else{
      //       this.$message.error(status)
      //     }
      // })
    }
  }
}
</script>

<style scoped>

</style>