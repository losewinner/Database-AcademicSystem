<template>
  <el-container style="display: flex;flex-direction: column;margin-top:15px">
    <el-container el-container style="height: 20px; margin-top:5px">
    <el-radio v-model="radio" label="1" @change="reload(1)">选课</el-radio>
    <el-radio v-model="radio" label="2" @change="reload(2)">退课</el-radio>
    </el-container>
    <el-container v-if="radio==1" style="display: flex;flex-direction: column;margin-top:0px">
      <el-container style="display: flex;flex-direction: row">
        <el-container el-container style="height: 20px; margin-top:15px">
        <el-select v-model="input.selectSemester" placeholder="请选择学期" style="width:30%; margin-right: 5px">
          <el-option v-for="item in optionSemester"
                     :key="item.value"
                     :label="item.label"
                     :value="item.value">
          </el-option>
        </el-select>
        <el-input placeholder="请输入课程号" suffix-icon="el-icon-s-management" v-model="input.courseId" style="width:30% ;margin-right: 5px"></el-input>
        <el-input placeholder="请输入课程名字" suffix-icon="el-icon-s-management" v-model="input.courseName" style="width:30% ;margin-right: 5px"></el-input>
        <el-container style="flex-direction: column;width: 200px">
         <el-button @click="searchClick()" type="primary" icon="el-icon-search">搜索</el-button>
        </el-container>
      </el-container>
    </el-container>
    <el-container class="查找结果表格" style="display:flex; flex-direction:column;margin-top: 30px">
      <el-table
          :data="FromDbInfoForSel"
          style="width: 100%"
          max-height="auto"
          @selection-change="handleSelectionChangeForSel">
        <el-table-column fixed="left"
                         prop="semester" label="学期" width="150">
        </el-table-column>
        <el-table-column
            prop="courseId" label="课程号" width="150">
        </el-table-column>
        <el-table-column
            prop="courseName" label="课程名字" width="150">
        </el-table-column>
          <el-table-column
                  prop="courseCredit" label="学分" width="150">
          </el-table-column>
          <el-table-column
                  prop="classTime" label="上课时间" width="200">
          </el-table-column>
        <el-table-column
            prop="remnant" label="剩余容量" width="100">
        </el-table-column>
        <el-table-column
            prop="volume" label="课程容量" width="100">
        </el-table-column>
        <el-table-column
            fixed="right" label="操作" width="170">
          <template slot-scope="scope">
            <el-button  @click="selectCourse(scope.row)" type="text" size="medium">选课</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pagenum"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total" style="margin-top: 10px;margin-bottom: 10px">
        </el-pagination>
    </el-container>
    </el-container>
    <el-container v-if="radio==2" style="display: flex;flex-direction: column;margin-top:0px">
        <el-container el-container style="height: 20px; margin-top:15px">
            <el-select v-model="input.selectSemester" placeholder="请选择学期" style="width:30%; margin-right: 5px">
                <el-option v-for="item in optionSemester"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
            </el-select>
            <el-container style="flex-direction: column;width: 200px">
                <el-button @click="refreshClick()" type="primary" icon="el-icon-refresh" style="width: 120px">刷新</el-button>
            </el-container>
        </el-container>
      <el-container class="查找结果表格" style="display:flex; flex-direction:column;margin-top: 30px">
        <el-table
            :data="FromDbInfoForDel"
            style="width: 100%"
            max-height="auto">
            <el-table-column fixed="left"
                             prop="semester" label="学期" width="150">
            </el-table-column>
            <el-table-column
                    prop="courseId" label="课程号" width="150">
            </el-table-column>
            <el-table-column
                    prop="courseName" label="课程名字" width="150">
            </el-table-column>
            <el-table-column
                    prop="courseCredit" label="学分" width="150">
            </el-table-column>
            <el-table-column
                    prop="classTime" label="上课时间" width="200">
            </el-table-column>
          <el-table-column
              fixed="right" label="操作" width="170">
            <template slot-scope="scope">
              <el-button
                  @click.native.prevent="dropCourse(scope.row)"
                  type="text"
                  size="medium" style="color:red">
                退课
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-container>
    </el-container>
  </el-container>

</template>


<script>
import axios from "axios";

export default {
  name: "SelectCourse",
  data(){
    //如果data里面什么都不写，页签跳转会出问题！
    return{
      radio:'1',
      optionSemester:[], //后端导入学期表，获得学期
      selectionOfSearch:'',
        selectedRowIndexes:[],
        pagesize:5,
        pagenum:1,
        totalpage:0,
        total:0,
      maxOptionValue:1,
      input:{        //前端输入，通过前端此字典绑定，然后去后端查找
        courseId:'',
        courseName:'',
        selectSemester:'',
      },
      FromDbInfoForSel:[],
      FromDbInfoForDel:[],
        allClassTime: [],
      ToDbInfo:[],
    }
  },
  methods:{
      reload(radi) {
          if(this.input.selectSemester==='') {
              return;
          }
          var semester_dict = this.optionSemester.find(x => x.value === this.input.selectSemester);
          var semester = semester_dict.label;
          if(radi===1) {
              //按照学生和课程的输入来选择
              console.log("semester ", semester);
              axios.post("/selectcourse/getStuSelPage", {
                  param: {
                      semester: semester,
                      studentId: '',
                      courseId: this.input.courseId,
                      courseName: this.input.courseName
                  }
              }).then(res => res.data).then(res => {
                  console.log("所有课程", res.data)
                  this.totalpage = Math.ceil(res.total / this.pagesize);
                  this.total = res.total;
                  axios.post("/selectcourse/getStuSelCourse", {
                      pagesize: this.pagesize,
                      pagenum: this.pagenum,
                      param: {
                          semester: semester,
                          studentId: '',
                          courseId: this.input.courseId,
                          courseName: this.input.courseName
                      }
                  }).then(res => res.data).then(res => {
                      if (res.code == "200") {
                          this.FromDbInfoForSel = res.data;
                          console.log("选课", this.FromDbInfoForSel)
                      }
                  })
              })
          }
          else{
              //按照学生和课程的输入来选择
              console.log("semester ",semester);
              axios.post("/selectcourse/getAllStuCourse?semester="+semester+"&studentId="+localStorage.getItem("userid")).then(res=>res.data).then(res=>{
                  console.log("？？？",res.data);
                  this.FromDbInfoForDel = res.data;
              })
          }
      },
      searchClick(p=1){
          this.refreshClick(0);  // 用于选课判断
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
          //然后获取对应的数据
          //解析所选的学期
          var semester_dict = this.optionSemester.find(x=>x.value===this.input.selectSemester);
          var semester = semester_dict.label;
          //按照学生和课程的输入来选择
          console.log("semester ",semester);
          axios.post("/selectcourse/getStuSelPage",{
              param:{
                  semester:semester,
                  studentId:'',
                  courseId:this.input.courseId,
                  courseName:this.input.courseName
              }
          }).then(res=>res.data).then(res=>{
              console.log("所有课程",res.data)
              this.totalpage = Math.ceil(res.total/this.pagesize);
              this.total=res.total;
              axios.post("/selectcourse/getStuSelCourse",{
                  pagesize:this.pagesize,
                  pagenum:this.pagenum,
                  param:{
                      semester:semester,
                      studentId:'',
                      courseId :this.input.courseId,
                      courseName: this.input.courseName
                  }
              }).then(res=>res.data).then(res=>{
                  if(res.code=="200"){
                      this.FromDbInfoForSel = res.data;
                      console.log("选课",this.FromDbInfoForSel)
                      if(p==1) {
                          this.$message({
                              type: 'success',
                              message: `查找成功！`,
                          });
                      }
                  }
              })
          })
      },
      refreshClick(p=1){
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
          //然后获取对应的数据
          //解析所选的学期
          var semester_dict = this.optionSemester.find(x=>x.value===this.input.selectSemester);
          var semester = semester_dict.label;
          //按照学生和课程的输入来选择
          console.log("semester ",semester);
          axios.post("/selectcourse/getAllStuCourse?semester="+semester+"&studentId="+localStorage.getItem("userid")).then(res=>res.data).then(res=>{
              console.log("？？？",res.data);
              this.FromDbInfoForDel = res.data;
              if(p==1) {
                  this.$message({
                      type: 'success',
                      message: `查找成功！`
                  })
              }
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

                  if(item.status===1)       //未能结课的学期才能选课退课
                  {
                      let newDict={};
                      newDict['value'] = '选项'+that.maxOptionValue;
                      newDict['label'] = item.semester;
                      that.optionSemester.push(newDict);
                      that.maxOptionValue+=1;
                  }

              }
              console.log('wwwwwww',that.optionSemester);
          })
      },
      parseClassTime(classTime) {
          // 解析上课时间，返回[day, start, end]数组
          let day = this.parseDay(classTime);
          let [start, end] = classTime.slice(2).split("-");
          return [day, parseInt(start), parseInt(end)];
      },
      parseDay(classTime) {
          // 解析上课时间中的星期几，返回0-6表示周一至周日
          let dayStr = classTime.match(/周(.)\d+-\d+/)[1];
          let dayMap = new Map([
              ["一", 1],
              ["二", 2],
              ["三", 3],
              ["四", 4],
              ["五", 5],
              ["六", 6],
              ["日", 7],
          ]);
          return dayMap.get(dayStr);
      },
      selectCourse(row){
          console.log("sel", row);
          if(row.remnant<=0)
          {
              this.$message({
                  type: 'error',
                  message: `选课失败！课程容量不足！`
              })
              return;
          }
          for(const item of this.FromDbInfoForDel)
          {
              if(row.courseId==item.courseId&&row.courseName==item.courseName&&row.staffId==item.staffId)
              {
                  this.$message({
                      type: 'error',
                      message: `选课失败！已选同类型课程！`
                  })
                  return;
              }
          }

          for(const item of this.FromDbInfoForDel)
          {
              let str0 = row.classTime;
              let newList = str0.split(","); // 新选课时间
              let str1 = item.classTime;
              let oldList = str1.split(","); // 已选课时间
              for(const i of newList)
              {
                  for(const j of oldList)
                  {
                      let [day0, start0, end0] = this.parseClassTime(i); // 解析上课时间
                      let [day1, start1, end1] = this.parseClassTime(j);
                      if(day0==day1)
                      {
                          if((start0<=start1&&start1<=end0) ||(start1<=start0&&start0<=end1))
                          {
                              this.$message({
                                  type: 'error',
                                  message: `选课失败！选课时间冲突！`
                              })
                              return;
                          }
                      }
                  }
              }
          }
          // 修改开课表中remnant-1，selectcourse表中新增数据
          // console.log("？？？？？", localStorage.getItem("userid"));
          axios.post("/selectcourse/selectCourse?studentId="+localStorage.getItem("userid")
              +"&semester="+row.semester
              +"&courseId="+row.courseId
              +"&staffId="+row.staffId
              +"&classTime="+row.classTime
          ).then(res=>res.data).then(res=> {
              if (res.code == "200") {
                  this.$message({
                      type: 'success',
                      message: `选课成功！`
                  })
                  this.searchClick(0);
/*                  let num = -1;
                  axios.post("/selectcourse/changeNum?num="+num
                      +"&semester="+row.semester
                      +"&courseId="+row.courseId
                      +"&staffId="+row.staffId
                      +"&classTime="+row.classTime
                  ).then(res=>res.data).then(res=> {
                      if (res.code == "200") {
                          this.$message({
                              type: 'success',
                              message: `选课成功！`
                          })
                          this.searchClick(0);
                      }
                  })*/
              }
          })

      },
      dropCourse(row){
          console.log("del", row);
          // 修改开课表中remnant+1，selectcourse表中删除数据
          axios.post("/selectcourse/dropCourse?studentId="+row.studentId
              +"&semester="+row.semester
              +"&courseId="+row.courseId
              +"&staffId="+row.staffId
              +"&classTime="+row.classTime
          ).then(res=>res.data).then(res=> {
              if (res.code == "200") {
                  this.$message({
                      type: 'success',
                      message: `退课成功！`
                  })
                  this.refreshClick(0);
/*                  let num = 1;
                  axios.post("/selectcourse/changeNum?num="+num
                      +"&semester="+row.semester
                      +"&courseId="+row.courseId
                      +"&staffId="+row.staffId
                      +"&classTime="+row.classTime
                  ).then(res=>res.data).then(res=> {
                      if (res.code == "200") {
                          this.$message({
                              type: 'success',
                              message: `退课成功！`
                          })
                          this.refreshClick(0);
                      }
                  })*/
              }
          })

      },
      handleCurrentChange(currentPage) {
          // 分页有问题：为什么有问题呢？
          // 是因为数据库也分页，然后它传来前端的时候，总数据只有一页的长度，所以totalpage会出问题
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
      handleSelectionChangeForSel(val){
          this.ToDbInfo = val;
          console.log("选择框",this.ToDbInfo);
          this.selectedRowIndexes = val.map(item => this.FromDbInfoForSel.indexOf(item));
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