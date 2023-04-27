<template>
    <el-container style="height: 100vh; border: 1px solid #eee">
        <el-header style="display:flex;justify-content: space-between; text-align: right; font-size: 12px;height:10%">
            <el-container class="left" style="align-items: center; display: flex ">
                <el-image :src="src" style="width: 10%; height: 100%"></el-image>
                <span style="font-size: 24px;font-weight: bolder;color:#234554">  教务管理系统</span>
            </el-container>
            <el-container class="right" style="align-items: center; display: flex; justify-content: flex-end">
              <span>
                <el-dropdown>
                  <i class="el-icon-setting" style="margin-right: 15px"></i>
                    <el-dropdown-menu slot="dropdown">
                      <el-dropdown-item>查看</el-dropdown-item>
                      <el-dropdown-item>新增</el-dropdown-item>
                      <el-dropdown-item>删除</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
              欢迎来到教学系统  管理员
              </span>
            </el-container>
        </el-header>

        <el-container style="height:70%; border: 1px solid #eee">
          <el-aside width="200px" style="background-color: rgb(10,16,26)">
              <el-menu :default-openeds="['1']" style="background-color:#333333">
                  <el-submenu index="1" style="color:#c42d2d">
                      <template slot="title"><i class="el-icon-s-data" style="color:#e8ce3d;font-weight: bolder"></i>
                          <span style="color:#e8ce3d;font-weight: bolder">统计分数</span>
                      </template>
                      <!--<el-menu-item index="1-3">选项1</el-menu-item>-->
                  </el-submenu>
                  <el-submenu index="2">
                      <template slot="title"><i class="el-icon-s-order" style="color:#e8ce3d;font-weight: bolder"></i>
                          <span style="color:#e8ce3d;font-weight: bolder">统计排名</span>
                      </template>
                  </el-submenu>
                  <el-submenu index="3">
                      <template slot="title"><i class="el-icon-data-analysis" style="color:#e8ce3d;font-weight: bolder"></i>
                          <span style="color:#e8ce3d;font-weight: bolder">统计挂科率</span>
                      </template>
                      <el-menu-item-group>
                          <template slot="title">分组一</template>
                          <el-menu-item index="3-1">选项1</el-menu-item>
                          <el-menu-item index="3-2">选项2</el-menu-item>
                      </el-menu-item-group>
                      <el-menu-item-group title="分组2">
                          <el-menu-item index="3-3">选项3</el-menu-item>
                      </el-menu-item-group>
                      <el-submenu index="3-4">
                          <template slot="title">选项4</template>
                          <el-menu-item index="3-4-1">选项4-1</el-menu-item>
                      </el-submenu>
                  </el-submenu>
              </el-menu>
          </el-aside>

          <el-container>
              <el-main>
                  <el-form :data="item">
                      <el-form-item prop="date" label="日期" width="140">
                      </el-form-item>
                      <el-form-item prop="name" label="姓名" width="120">
                      </el-form-item>
                      <el-form-item prop="address" label="地址">
                      </el-form-item>
                  </el-form>
              </el-main>
          </el-container>
      </el-container>
    </el-container>
</template>

<script>

export default {
    name:'Admin',
    methods: {
        GetCurrentUser(){
            this.$axios.get('')
        }
    },
    data() {
        return {
            item:{
                date: '2016-05-02',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            },
            src:'https://th.bing.com/th/id/R.c69363424aae28fd1b22b4b8350f9d8d?rik=QuBXzKksKrbbuw&riu=http%3a%2f%2fit.vsharing.com%2fUtil%2fXX%2fEntLogo%2f460.jpg&ehk=%2f1lN3Me3DX2bn%2by52EoSQ3%2fJ7GZc%2fWkjLoZMhwdSVsg%3d&risl=&pid=ImgRaw&r=0'
        }
    }
};
</script>

<style scoped>
  .el-header {
      background-color: #ffffff;
      color: #333;
      line-height: 60px;
      margin-top: 0;
  }

  .el-aside {
      color: #d3aeae;

  }

  body > .el-container {
      margin-bottom: 0px;
      margin-top: 0px;
      height:100%;
  }
</style>