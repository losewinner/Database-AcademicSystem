<template>
<div>
  <el-select v-model="couoption" placeholder="请选择课程">
    <el-option
        v-for="item in cou_options"
        :key="item.key"
        :label="item.label"
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
            style="width: 47%">
    <el-table-column prop="studentid" label="学号" width="120" sortable></el-table-column>
    <el-table-column prop="name" label="姓名" width="120" ></el-table-column>
    <el-table-column prop="signscore" label="已签到" width="120" >
      <template v-slot="scope">
        <el-radio v-model="data[scope.$index].sign" label="1"></el-radio>
      </template>
    </el-table-column>
    <el-table-column prop="signscore" label="未签到" width="120" >
      <template v-slot="scope">
        <el-radio v-model="data[scope.$index].sign" label="0"></el-radio>
      </template>
    </el-table-column>
  </el-table>
  <el-button type="success" @click.native.prevent="uploadsign">提交本次签到<i class="el-icon-edit"></i></el-button>
</div>

</template>

<script>
import axios from "axios";

export default {
  name: "SignIn",
  data(){
    return{
      activeName:"first",
      data:[],
      dataLen:0,
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
      claoption:"",
      cla_id:new Map(),
      ratio:0,
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      console.log("loading")
      //请求课程_上课时间
      axios.get("http://localhost:8080/opencourse/getclass",{
        params:{
          staffid:JSON.parse(sessionStorage.getItem("curuser")).staffid
        }
      }).then(res=>res.data).then(res=>{
        if(res.code=="200"){
          this.cou_options=[]
          this.cla_id =new Map()
          for(let i=0;i<res.data.length;++i){
            if(!this.cla_id.has(res.data[i].coursename)){
              this.cla_id.set(res.data[i].coursename,res.data[i].courseid)
            }
            if(!this.cla_options.has(res.data[i].coursename)){
              this.cla_options.set(res.data[i].coursename,[res.data[i].classtime])
            }
            else{
              let mget=this.cla_options.get(res.data[i].coursename)
              mget.push(res.data[i].classtime)
              this.cla_options.set(res.data[i].coursename, mget)
            }
          }
          let list=Array.from(this.cla_id.keys())
          for (const listKey in list) {
            this.cou_options.push({
              value:list[listKey] ,
              lable:this.cla_id.get(list[listKey])
            })
          }
        }
      })
    },
    select(){
      axios.get("http://localhost:8080/course/getratio?courseid="
          +this.cla_id.get(this.couoption)).then(res=>res.data).then(res=>{
        if(res.code=="200"){
          this.ratio = res.data
          console.log("success"+this.ratio)
        }
        else{
          console.log("failed")
        }
      })
      axios.post("http://localhost:8080/selectcourse/getstudent",{
        pagesize:100,
        pagenum:1,
        param:{
          courseid:this.cla_id.get(this.couoption),
          staffid:JSON.parse(sessionStorage.getItem("curuser")).staffid,
          classtime:this.claoption
        }
      }).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code=="200"){
          this.data=res.data
        }
      })

    },
    uploadsign(){
      let res=[]
      for (const re of this.data) {
        console.log(re.sign)
        if (re.sign=="1") {
          res.push(re)
        }
        else if (re.sign!="0"){
          this.$alert("请补全"+re.studentid+re.name+"签到情况")
          return
        }
        re.sign="0"
      }
      console.log(res)
      console.log("签到成功")
      axios.post("http://localhost:8080/selectcourse/uploadsign",
          res).then(res=>res.data).then(res=>{
        if(res.code=="200"){
          this.$message(res.msg)
        }
        else{
          this.$alert(res.msg);
        }
      })
    },

  }
}
</script>

<style scoped>

</style>