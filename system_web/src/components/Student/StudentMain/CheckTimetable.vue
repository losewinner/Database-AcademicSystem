<template>
  <el-container style="display: flex;flex-direction: column;margin-top:15px">
    <el-select v-model="input.selectSemester" placeholder="请选择学期" style="width:30%">
    <el-option v-for="item in optionSemester"
               :key="item.value"
               :label="item.label"
               :value="item.value">
    </el-option>
  </el-select>
    <table class="tblouter">
    <tbody>
    <tr>
      <td style="padding:0px;">
        <table class="tblnoborder">
          <tbody><tr>
            <th style="width: 2em;">#</th>
            <th style="width: 7em;" datahtmllocale="classhours">上课时间</th>
            <th>一</th>
            <th>二</th>
            <th>三</th>
            <th>四</th>
            <th>五</th>
            <th>六</th>
            <th>日</th>
          </tr>

          <tr name="rowweek" style="">
            <td style="text-align:center;">1</td>
            <td style="text-align:center;">8:00-8:45</td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
          </tr>

          <tr name="rowweek" style="">
            <td style="text-align:center;">2</td>
            <td style="text-align:center;">8:55-9:40</td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
          </tr>

          <tr name="rowweek" style="">
            <td style="text-align:center;">3</td>
            <td style="text-align:center;">10:00-10:45</td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
          </tr>

          <tr name="rowweek">
            <td style="text-align:center;">4</td>
            <td style="text-align:center;">10:55-11:40</td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
          </tr>

          <tr name="rowweek">
            <td style="text-align:center;">5</td>
            <td style="text-align:center;">13:00-13:45</td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
          </tr>

          <tr name="rowweek">
            <td style="text-align:center;">6</td>
            <td style="text-align:center;">13:55-14:40</td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
          </tr>

          <tr name="rowweek">
            <td style="text-align:center;">7</td>
            <td style="text-align:center;">15:00-15:45</td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
          </tr>

          <tr name="rowweek">
            <td style="text-align:center;">8</td>
            <td style="text-align:center;">15:55-16:40</td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
          </tr>

          <tr name="rowweek">
            <td style="text-align:center;">9</td>
            <td style="text-align:center;">18:00-18:45</td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
          </tr>

          <tr name="rowweek">
            <td style="text-align:center;">10</td>
            <td style="text-align:center;">18:55-19:40</td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
          </tr>

          <tr name="rowweek">
            <td style="text-align:center;">11</td>
            <td style="text-align:center;">20:00-20:45</td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
          </tr>

          <tr name="rowweek">
            <td style="text-align:center;">12</td>
            <td style="text-align:center;">20:55-21:40</td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
            <td style="text-align:center;"></td>
          </tr>
          </tbody>
        </table>
        <script type="/Student/Timetable">
          $(function () {
            InitRowHover("rowweek");
          });
        </script>
      </td>
    </tr>
    </tbody>
  </table>
  </el-container>
</template>

<script>
export default {
  name: "CheckTimetable",
  data(){
    //如果data里面什么都不写，页签跳转会出问题！
    return {
      optionSemester: [{value: '选项1', label: '2023春季'}], //后端导入学期表，获得学期
      input: {        //前端输入，通过前端此字典绑定，然后去后端查找
        studentId: '',
        selectSemester: '',
      },
      FromDbInfo: [{         //后端通过前端的输入查找到的信息放在这个字典数组中，
        courseName: 'BB88',
        courseTime: '周一1-2，周二3-4'
      }]
    }
  },
  methods:{
  }
}
</script>

<style scoped>

</style>
