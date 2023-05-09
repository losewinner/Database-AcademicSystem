<template>
  <div>
    <div class="echart" id="mychart" :style="myChartstyle"></div>
    <div class="echart" id="mychart1" :style="myChartstyle"></div>
    <div class="echart" id="mychart2" :style="myChartstyle"></div>
  </div>

</template>

<script>
import * as echarts from "echarts";
export default {
  name: "SignIn",
  data(){
    return{
      myChartstyle:{float:"left",width:"33%",height:"400px"}
    }
  },
  mounted() {
    this.initEchart()
  }
  ,
  methods:{
    initEchart(){
      const option ={
        xAxis:{
          data:[1,2,3,4]
        },
        yAxis:{

        },
        series:[
          {
            type:'bar',
            data:[4,5,6,7]
          }
        ]
      };
      const myChart = echarts.init(document.getElementById("mychart"));
      const myChart1 = echarts.init(document.getElementById("mychart1"));
      const myChart2 = echarts.init(document.getElementById("mychart2"));
      myChart.setOption(option);
      myChart1.setOption(option);
      myChart2.setOption(option);
    }
  }
}
</script>

<style scoped>

</style>