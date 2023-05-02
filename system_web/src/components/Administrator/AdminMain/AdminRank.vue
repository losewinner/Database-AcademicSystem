<template>
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

  </el-container>
</template>

<script>
export default {
    name: "AdminRank",
    data(){
      return{
          optionSemester:[{value:'选项1',label:'2023春季'}], //后端导入学期表，获得学期
          selectSemester:'',
          optionDept:[{value:'选项1',label:'计算机工程与科学学院'}], //后端导入院系表，获得院系名字
          selectDept:'',
          selectionOfSearch:'',
          input:{
              courseName:'',
          }
      }
    },

}
</script>

<style scoped>

</style>