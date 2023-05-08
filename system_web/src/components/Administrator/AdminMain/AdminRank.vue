<template>
    <el-container class="整个部分" style="margin-top: 10px;display: flex;flex-direction: column">
        <el-container class="查找框选部分" style="margin-top: 10px;display:flex;flex-direction: column">
            <el-select v-model="selectSemester" placeholder="请选择学期" style="margin-right:5px;width:30%">
                <el-option v-for="item in optionSemester"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
            </el-select>
            <el-radio-group v-model="selectionOfSearch" style="margin-top: 10px">
                <el-radio-button label="dept">按照院系</el-radio-button>
                <el-radio-button label="course">按照课程</el-radio-button>
            </el-radio-group>
            <el-container class="二选一" style="margin-top: 10px;width:30%">
                <el-form v-if="selectionOfSearch === 'dept'">
                    <el-form-item label="选择院系">
                        <el-select v-model="optionDept" placeholder="请选择">
                            <el-option v-for="item in optionDept"
                                       :key="item.value"
                                       :label="item.label"
                                       :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <el-form v-else-if="selectionOfSearch === 'course'">
                    <el-form-item label="搜索课程">
                        <el-input v-model="input.courseName" placeholder="请输入课程名称"></el-input>
                    </el-form-item>
                </el-form>
            </el-container>
            <el-container class="查找，删除按钮" style="margin-top: 5px;display: flex;flex-direction: row">
                <el-button @click="searchClick()" type="primary" icon="el-icon-search" style="margin-right: 10px">搜索</el-button>
            </el-container>
        </el-container>

        <el-container class="展示排名部分" style="margin-top: 15px">
            <el-table ref = "RankInfo" :data="FromDbInfo">
                <el-table-column fixed="left"
                                 prop = "semester" label = "学期" width = "150">
                </el-table-column>
                <el-table-column
                    prop="studentId" label="学号" width="130" sortable>
                </el-table-column>
                <el-table-column
                    prop="studentName" label="学生姓名" width="120">
                </el-table-column>
                <el-table-column
                    prop="deptName" label="院系" width="150" v-if="deptShow">
                </el-table-column>
                <el-table-column
                    prop="courseId" label="课程号" width="150" v-if="courseShow">
                </el-table-column>
                <el-table-column
                    prop="courseName" label="课程名字" width="150" v-if="courseShow">
                </el-table-column>
                <el-table-column
                    prop="finalScore" label="综合成绩" width="150" v-if="courseShow">
                </el-table-column>
                <el-table-column
                    prop="scorePoint" label="绩点" width="150">
                </el-table-column>
            </el-table>

        </el-container>
        <el-pagination
            @current-change="handleCurrentChange"
            :current-page="pagination.pagenum"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="pagination.pagesize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pagination.total"
        />
    </el-container>

</template>

<script>
import axios from "axios";

export default {
    name: "AdminRank",
    data(){
      return{
          maxOptionSemester:1,
          maxOptionDept:1,
          optionSemester:[], //后端导入学期表，获得学期
          selectSemester:'',
          optionDept:[], //后端导入院系表，获得院系名字
          selectDept:'',
          selectionOfSearch:'',
          input:{
              courseName:'',
          },
          courseShow : false,
          deptShow:false,
          FromDbInfo:[],
          pagination:{
              pagenum:1,
              pagesize:10,
              total:0,
          },

      }
    },
    methods:{
        loadData(){
            console.log("loadsomething")
            //获取学期信息
            axios.get("/semestatus/list").then(res=>{
                console.log("获取学期成功",res.data);    //获取成功
                for(const item of res.data)
                {
                    if(item.status===0)       //未能结课的学期不放进列表中
                    {
                        //continue;
                    }
                    let newDict={};
                    newDict['value'] = '选项'+this.maxOptionSemester;
                    newDict['label'] = item.semester;
                    this.optionSemester.push(newDict);
                    this.maxOptionSemester+=1;
                }
                console.log('学期选择框',this.optionSemester);
            })
            //获取学院信息
            axios.get("/dept/list").then(res=>{
                console.log("",res.data);    //获取成功
                for(const item of res.data)
                {
                    let newDict={};
                    newDict['value'] = '选项'+this.maxOptionDept;
                    newDict['label'] = item.semester;
                    this.optionDept.push(newDict);
                    this.maxOptionDept+=1;
                }
                console.log('wwwwwww',this.optionDept);
            })
        },
        handleCurrentChange(val){
          this.currentPage = val;
          this.loadData();
        },
        searchClick(){
            console.log("搜索");
        }
    },
    created() {
      this.loadData();
    }

}
</script>

<style scoped>

</style>