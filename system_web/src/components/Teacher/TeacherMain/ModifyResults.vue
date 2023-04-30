<template>
  <div>
    ModifyResult

    <el-table :data="tableData"
              style="width: 100%"
              >
      <el-table-column prop="studentId" label="学号" width="120">
        <template v-slot="scope">
          <div v-html="scope.row.studentId"></div>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ModifyResults",
  data(){
    return{
      tableData:[],

    }
  },
  created() {
    this.load()

  },
  methods:{
    load(){
      console.log("loading")
      this.semester=1
      this.courseId=1
      this.staffId=1
      this.classTime=1
      axios.get("http://localhost:8080//selectcourse/allstudent?semester="+this.semester+"&courseId="+this.courseId+"&staffId="+
      this.staffId+"&classTime="+this.classTime).then(response=>{
          const {
            data,
            status
          }=response;
          if(status === "200"){
            this.tableData = data.total;
            console.log(this.tableData)
          }
          else{
            this.$message.error(status)
          }
      }).catch((error)=>{
        console.log(error)

      }).then(()=>{
        console.log("all done")
      })
    }
  }
}
</script>

<style scoped>

</style>