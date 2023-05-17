<template>
  <div>
    <el-table :data="teaclass"
              border
              style="width: 100% ">
      <el-table-column
          prop="0"
          label="课程号"
          width="100">
      </el-table-column>
      <el-table-column
          prop="1"
          label="课程名"
          width="220">
      </el-table-column>
      <el-table-column
          prop="2"
          label="上课时间"
          width="220">
      </el-table-column>
      <el-table-column
          prop="3"
          label="上课地点"
          width="220">
      </el-table-column>
    </el-table>
    <el-table :data="teatime"
              style="width: 100%">
      <el-table-column
          prop="0"
          label="时间"
          width="100">
      </el-table-column>
      <el-table-column
          prop="1"
          label="星期一"
          width="220">
      </el-table-column>
      <el-table-column
          prop="2"
          label="星期二"
          width="220">
      </el-table-column>
      <el-table-column
          prop="3"
          label="星期三"
          width="220">
      </el-table-column>
      <el-table-column
          prop="4"
          label="星期四"
          width="220">
      </el-table-column>
      <el-table-column
          prop="5"
          label="星期五"
          width="220">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Timetable",
  data(){
    return{
      teaclass:[
        {0:" ",1:" ",2:" ",3:"-"},
        {0:" ",1:" ",2:" ",3:"-"},
        {0:" ",1:" ",2:" ",3:"-"},
        {0:" ",1:" ",2:" ",3:"-"},
        {0:" ",1:" ",2:" ",3:"-"},
      ],
      teatime:[
        {0:"8:00-8:45",1:"",2:"",3:"",4:"",5:""},
        {0:"8:55-9:40",1:"",2:"",3:"",4:"",5:""},
        {0:"10:00-10:40",1:"",2:"",3:"",4:"",5:""},
        {0:"10:55-11:40",1:"",2:"",3:"",4:"",5:""},
        {0:"13:00-13:45",1:"",2:"",3:"",4:"",5:""},
        {0:"13:55-12:40",1:"",2:"",3:"",4:"",5:""},
        {0:"15:00-15:45",1:"",2:"",3:"",4:"",5:""},
        {0:"15:55-16:40",1:"",2:"",3:"",4:"",5:""},
        {0:"18:00-18:40",1:"",2:"",3:"",4:"",5:""},
        {0:"18:55-19:40",1:"",2:"",3:"",4:"",5:""},
        {0:"20:00-20:45",1:"",2:"",3:"",4:"",5:""},
        {0:"20:55-21:40",1:"",2:"",3:"",4:"",5:""},
      ],
    }
  },
  created(){
    console.log(JSON.parse(sessionStorage.getItem("curuser")).staffid)
    console.log('timetable')
    axios.get("http://localhost:8080/opencourse/getclass",{
      params:{
        staffid:JSON.parse(sessionStorage.getItem("curuser")).staffid
      }
    }).then(res=>res.data).then(res=>{
      if(res.code=="200"){
        for (let k = 0;k<res.data.length;++k) {
          let dataElement = res.data[k]
          let classtime = dataElement.classtime
          let classname = dataElement.coursename
          this.teaclass[k][0]=dataElement.courseid
          this.teaclass[k][1]=classname
          this.teaclass[k][2]=classtime
          this.teaclass[k][3]=dataElement.address
          for (const dataElementElement of classtime.split(',')) {
            // 周dy-z
            let classday = this.day_format(dataElementElement[1])
            let classwhen = dataElementElement.substr(2).split('-')
            for (let i = classwhen[0]; i <= classwhen[1]; i++) {
              //   y~z
              this.teatime[i-1][classday] = classname
            }
          }
          console.log(this.teatime)
        }
      }
    });
  },
  methods:{
    day_format(day){
      if (day==='一') return 1
      else if (day==='二') return 2
      else if (day==='三') return 3
      else if (day==='四') return 4
      else  return 5
    }
  }
}
</script>

<style scoped>

</style>