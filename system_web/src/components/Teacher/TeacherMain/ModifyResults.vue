<template>
  <div>
    <el-select v-model="couoption" placeholder="请选择课程">
      <el-option
          v-for="item in cou_options"
          :key="item.key"
          :label="item.value"
          :value="item.value"
      >
      </el-option>
    </el-select>
    <el-select v-model="claoption" placeholder="请选择班级">
      <el-option
          v-for="item in cla_options.get(couoption)"
          :key="item"
          :label="item"
          :value="item">
      </el-option>
    </el-select>
    <el-button type="primary" @click="select">搜索</el-button>
    <el-table :data="data"
              style="width: 100%">
        <el-table-column prop="studentid" label="学号" width="120" sortable></el-table-column>
        <el-table-column prop="name" label="姓名" width="120" ></el-table-column>
        <el-table-column prop="signscore" label="平时成绩" width="120" sortable></el-table-column>
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
      staffid:"",
      cou_options:[
        {
          value:1
        },
        {
          value:2
        },
        {
          value:3
        },
      ],
      couoption:"",
      cla_options:new Map(),
      claoption:""
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      console.log("loading")
      this.staffid = JSON.parse(sessionStorage.getItem("curuser")).staffid
      //请求课程_上课时间
      axios.get("http://localhost:8080/opencourse/getclass",{
        params:{
          staffid:this.staffid
        }
      }).then(res=>res.data).then(res=>{
        if(res.code=="200"){
          this.cou_options=[]
          let set= new Set()
          for(let i=0;i<res.data.length;++i){
            set.add(res.data[i].coursename)
            if(!this.cla_options.has(res.data[i].coursename)){
              this.cla_options.set(res.data[i].coursename,[res.data[i].classtime])
            }
            else{
              let mget=this.cla_options.get(res.data[i].coursename)
              this.cla_options.set(res.data[i].coursename, mget)
            }
          }
          let list=Array.from(set)
          for (const listKey in list) {
            this.cou_options.push({
              value: list[listKey]
            })
          }

        }
      })
    },
    select(){
      console.log("请求数据")
      axios.post("http://localhost:8080/selectcourse/getstudent",{
        params:{
          "pagesize":100,
          "pagenum":1,
          "param":{
            "courseid":this.cou_options,
            "staffid":this.staffid,
            "classtime":this.claoption
          }
        }
      }).then(res=>res.data).then(res=>{
        if(res.code=="200"){
          console.log(res)
        }
      })
    }

  }
}
</script>

<style scoped>

</style>