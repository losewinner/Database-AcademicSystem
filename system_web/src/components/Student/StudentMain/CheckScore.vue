<template>
  <el-container el-container style="height: 600px; margin-top:15px">

    <el-container class="学生各科成绩" style="margin-top: 20px">
      <el-table
          :data="FromDbInfo"
          style="width: 70%"
          max-height="400">
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
            prop="courseCredit" label="学分" width="100">
        </el-table-column>
        <el-table-column
            prop="score" label="绩点" width="100">
        </el-table-column>
      </el-table>
    </el-container>

    <el-container class="学生各学期成绩" style="margin-top: 20px">
      <el-table
          :data="FromDbInfoDealed"
          style="width: 70%"
          max-height="400">
        <el-table-column fixed="left"
                         prop="semester" label="学期" width="150">
        </el-table-column>
        <el-table-column
            prop="averageScore" label="均绩" width="100">
        </el-table-column>
      </el-table>
    </el-container>

  </el-container>

</template>


<script>
// import axios from "axios"
export default {
  name: "CheckScore",
  data(){
    return{
      studentId:'',         // 获取登录用户的用户id
      FromDbInfo:[{         // 后端通过前端的输入查找到的信息放在这个字典数组中，
        semester: '2023春季',
        courseId: '12345',
        courseName:'BB88',
        courseCredit:4,
        score: 2003
      },
        {
          semester: '2023春季',
          courseId: '12345',
          courseName:'B998',
          courseCredit:6,
          score: 1998
        }],
      FromDbInfoDealed:[{
        semester: '2023春季',
        averageScore: (2003*4+1998*6)/(4+6)
      }],
    }
  },
  methods:{
    loadData(){         // 从数据库中获取信息

    },

    dealData(){         // 处理获取的考试信息，计算均绩

    }
  },
  created(){
    this.loadData();
    this.dealData();
  }
}
</script>

<style scoped>

</style>