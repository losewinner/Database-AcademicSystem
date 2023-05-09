<template>
  <div>
    <el-tabs v-model="activeName" >
      <el-tab-pane label="分数登记" name="first">
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
          <el-table-column prop="signscore" label="平时成绩" width="120" sortable >
            <template v-slot="scope">
              <el-input v-model="data[scope.$index].score" placeholder="请输入内容" min="0" max="100"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="testscore" label="考试成绩" width="120" sortable>
            <template v-slot="scope">
              <el-input v-model="data[scope.$index].testscore" placeholder="请输入内容" min="0" max="100"></el-input>
            </template>
          </el-table-column>
          <el-table-column label="总成绩" width="120" sortable v-text="ratio">
            <template v-slot="scope">
              {{ Math.round(data[scope.$index].testscore*(1-ratio/10)+data[scope.$index].score*ratio/10)}}
            </template>
          </el-table-column>
        </el-table>
        <el-button type="success" @click.native.prevent="modifyScores">提交成绩<i class="el-icon-edit"></i></el-button>
      </el-tab-pane>
      <el-tab-pane label="统计图表" name="second" >
        <div style="display: flex;align-items: center;justify-content: space-around;">
          <div class="echart" ref="mychart"  :style="myChartstyle"></div>
          <div class="echart" ref="mychart1" :style="myChartstyle"></div>
          <div class="echart" ref="mychart2" :style="myChartstyle"></div>
        </div>
        <div style="display: flex;align-items: center;justify-content: space-around;">
          <div>平时成绩</div>
          <div>考试成绩</div>
          <div>综合成绩</div>
        </div>

      </el-tab-pane>
    </el-tabs>

  </div>
</template>
<script>
import axios from "axios";
import * as echarts from "echarts";
export default {
  name: "ModifyResults",
  data(){
    return{
      activeName:"first",
      myChartstyle:{width:"450px",height:"400px"},
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
      ratio:0
    }
  },
  created() {
    this.load()
  },
  mounted() {
      this.initEchart();
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
          this.initEchart()
        }
      })

    },
    modifyScores(){
      console.log(this.data)
      console.log("更新数据...")
      axios.post("http://localhost:8080/selectcourse/updateScore",this.data).then(res=>res.data).then(res=>{
        if(res.code=="200"){
          console.log(res.msg);
          this.select()
        }
        else{
          this.$alert(res.msg);
        }
      })

    },
    rank(score){
      if(score>=90) return 5;
      if(score>=85) return 4;
      if(score>=75) return 3;
      if(score>=66) return 2;
      if(score>=60) return 1;
      return 0;
    },
    initEchart(){
      const xData=["<60","60-65","66-74","75-84","85-89",">=90"]
      const yData1=[0,0,0,0,0,0]
      const yData2=[0,0,0,0,0,0]
      const yData3=[0,0,0,0,0,0]
      for (const s of this.data) {
        yData1[this.rank(s.score)]++
        yData2[this.rank(s.testscore)]++
        yData3[this.rank(Math.round(s.testscore*(1-this.ratio/10)+s.score*this.ratio/10))]++
      }
      const option1 ={
        xAxis:{
          type:"category",
          data:xData
        },
        yAxis: {
            type:"value"
          }
        ,
        series:[
          {
            type:'bar',
            data:yData1,
            label:{
              show:true,
              position:"top"
            }
          }
        ],
      };
      const option2 ={
        xAxis:{
          data:xData
        },
        yAxis:{
        },
        series:[
          {
            type:'bar',
            data:yData2,
            label:{
              show:true,
              position:"top"
            }
          }
        ]
      };
      const option3 ={
        xAxis:{
          data:xData
        },
        yAxis:{
        },
        series:[
          {
            type:'bar',
            data:yData3,
            label:{
              show:true,
              position:"top"
            }
          }
        ]
      };
      const myChart = echarts.init(this.$refs.mychart);
      const myChart1 = echarts.init(this.$refs.mychart1);
      const myChart2 = echarts.init(this.$refs.mychart2);
      myChart.setOption(option1);
      myChart1.setOption(option2);
      myChart2.setOption(option3);
    }


  }
}
</script>

<style scoped>

</style>