<template>
  <el-container el-container style="display: flex;flex-direction: column; margin-top:15px">
    <el-container el-container style="height: 20px; margin-top:15px">
          <el-select v-model="selectSemester" placeholder="请选择学期" style="width:30%;margin-right: 5px">
              <el-option v-for="item in optionSemester"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
          </el-select>
          <el-button @click="searchClick()" type="primary" icon="el-icon-search" style="height: 40px;margin-right: 450px">搜索
          </el-button>
          <el-descriptions :column="1"  border >
              <el-descriptions-item>
                  <template slot="label">均绩</template>
                  {{averageScore}}
              </el-descriptions-item>
          </el-descriptions>
      </el-container>
    <el-container class="学生各科成绩" style="display: flex;flex-direction: column; margin-top: 40px">

        <el-table
          :data="FromDbInfo"
          style="width: 78%"
          max-height="auto"
          @selection-change="handleSelectionChange">
        <el-table-column
            prop="courseId" label="课程号" width="200">
        </el-table-column>
        <el-table-column
            prop="courseName" label="课程名字" width="200">
        </el-table-column>
        <el-table-column
            prop="courseCredit" label="学分" width="200">
        </el-table-column>
        <el-table-column
            prop="finalScore" label="综合成绩" width="200">
        </el-table-column>
        <el-table-column
            prop="scorePoint" label="绩点" width="200">
        </el-table-column>
      </el-table>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pagenum"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total" style="width: 600px; margin-top: 10px;margin-bottom: 10px">
        </el-pagination>
    </el-container>

  </el-container>

</template>


<script>
import axios from "axios";

export default {
  name: "CheckScore",
  data(){
    return{
        selectSemester:"",
        optionSemester:[], //后端导入学期表，获得学期
        selectedRowIndexes:[],
        maxOptionValue:1,
        FromDbInfo:[],
        AllScore:[],
        averageScore:0,
        pagesize:5,
        pagenum:1,
        totalpage:0,
        total:0,
    }
  },
  methods:{
      getAverageScore() {
          var sum_s=0.0,sum_n=0;
          console.log("yoyo",this.AllScore)
          for(const item of this.AllScore){
              sum_s+=item.courseCredit*item.finalScore;
              sum_n+=item.courseCredit;
          }
          sum_s/=sum_n;
          console.log("calc_s",sum_s)
          console.log("calc_n",sum_n)
          this.averageScore=this.ScoreTrans(sum_s);
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
      searchClick(){
          //搜之前做判断，如果学期没选，就不做搜索，提醒用户必须选择学期
          console.log("wtf",this.selectSemester)
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

          //然后获取对应的数据
          //解析所选的学期
          var semester_dict = this.optionSemester.find(x=>x.value===this.selectSemester);
          var semester = semester_dict.label;
          //按照学生和课程的输入来选择
          axios.post("/selectcourse/getStuPage",{
              param:{
                  semester:semester,
                  studentId:localStorage.getItem("userid"),
              }
          }).then(res=>res.data).then(res=>{
              this.totalpage = Math.ceil(res.total/this.pagesize);
              this.AllScore = res.data;
              this.total = res.total;
              console.log("所有成绩",this.AllScore);
              this.getAverageScore();
              axios.post("/selectcourse/getStuScore",{
                  pagesize:this.pagesize,
                  pagenum:this.pagenum,
                  param:{
                      semester:semester,
                      studentId:localStorage.getItem("userid"),
                  }
              }).then(res=>res.data).then(res=>{
                  if(res.code=="200"){
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
      },

      handleCurrentChange(currentPage) {
          //分页有问题：为什么有问题呢？
          //是因为数据库也分页，然后它传来前端的时候，总数据只有一页的长度，所以totalpage会出问题
          // 修改当前页码，并重新查询数据
          this.pagenum = currentPage;
          if(this.selectSemester==='')
          {
              this.loadData();
          }
          else{
              this.searchClick();
          }

      },
      handleSizeChange(pageSize){
          this.pagesize = pageSize;
          if(this.selectSemester==='')
          {
              this.loadData();
          }
          else{
              this.searchClick();
          }
      },
      handleSelectionChange(val){
          this.ToDbInfo = val;
          console.log("选择框",this.ToDbInfo);
          this.selectedRowIndexes = val.map(item => this.FromDbInfo.indexOf(item));
          this.selectedRowIndexes.sort((a,b)=>b-a)
          console.log(this.selectedRowIndexes);
      },

  },
  created(){
      this.loadData();
  }
}
</script>

<style scoped>

</style>