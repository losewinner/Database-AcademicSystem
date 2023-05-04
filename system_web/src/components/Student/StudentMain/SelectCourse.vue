<template>
  <el-container style="display: flex;flex-direction: column;margin-top:15px">
    <el-container el-container style="height: 20px; margin-top:5px">
    <el-radio v-model="radio" label="1">选课</el-radio>
    <el-radio v-model="radio" label="2">退课</el-radio>
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
      <el-container class="查找结果表格" style="margin-top: 30px">
      <el-table
          :data="FromDbInfoForSel"
          style="width: 70%"
          max-height="250">
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
            prop="remnant" label="可选人数" width="100">
        </el-table-column>
        <el-table-column
            prop="limit" label="开课人数" width="100">
        </el-table-column>
        <el-table-column
            fixed="right" label="操作" width="170">
          <template slot-scope="scope">
            <el-button  @click="editClick(scope.row)" type="text" size="medium">选课</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-container>
    </el-container>
    <el-container v-if="radio==2" style="display: flex;flex-direction: column;margin-top:0px">
      <el-container class="查找结果表格" style="margin-top: 0px">
        <el-table
            :data="FromDbInfoForSel"
            style="width: 70%"
            max-height="250">
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
              fixed="right" label="操作" width="170">
            <template slot-scope="scope">
              <el-button
                  @click.native.prevent="deleteRow(scope.$index, FromDbInfoForDel)"
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
import axios from "axios"
export default {
  name: "AdminScore",
  data(){
    //如果data里面什么都不写，页签跳转会出问题！
    return{
      radio:'1',
      optionSemester:[{value:'选项1',label:'2023春季'}], //后端导入学期表，获得学期
      selectionOfSearch:'',
      maxOptionValue:1,
      input:{        //前端输入，通过前端此字典绑定，然后去后端查找
        courseId:'',
        courseName:'',
        selectSemester:'',
      },
      FromDbInfoForSel:[{         //后端通过前端的输入查找到的信息放在这个字典数组中，
        semester: '2023春季',
        courseId: '12345',
        courseName:'BB88',
        limit:60,
        remnant:10
      }],
      FromDbInfoForDel:[{         //后端通过前端的输入查找到的信息放在这个字典数组中，
        semester: '2023春季',
        courseId: '12345',
        courseName:'BB88',
      }],
      ScoreAnalysis:{      //后端数据库制作三种分的视图，将三分记下来，放进这个字典中。
        averageScore:50,
        highestScore:100,
        lowestScore:0.
      }
    }
  },
  methods:{
    deleteRow(index,rows){     //不仅前端移除，且数据库选课表中也要把这个学生的元组删去。
      rows.splice(index,1);
    },
    editClick(row){
      console.log(row)
    },
    searchClick(){
      //搜之前做判断，如果学期没选，就不做搜索，提醒用户必须选择学期
      console.log("wtf")
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

      //先获取input的东西（v-model双向绑定自动获取了
      console.log(this.input);

    },
    loadData(){
      //向数据库请求数据，涉及：学期表，学生表，选课表，教师表
      console.log("w");
      //获取学期信息，放进optionSemester中
      //先尝试只获取学期表，
      axios.get("http://127.0.0.1:8080/semestatus/list").then(res=>{
        console.log("yeyeye",res.data);    //获取成功
        for(const item of res.data)
        {
          if(item.status===0)       //未能结课的学期不放进列表中
          {
            continue;
          }
          let newDict={};
          newDict['value'] = '选项'+(this.maxOptionValue+1);
          newDict['label'] = item.semester;
          this.optionSemester.push(newDict);
          this.maxOptionValue+=1;
        }
        console.log('wwwwwww',this.optionSemester);
      })


    }
  },
  created(){
    this.loadData();
  }
}
</script>

<style scoped>

</style>