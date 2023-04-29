<template>
    <div>
        <el-menu :default-openeds="[]" :style="{width:sideWidth+'px'}"
                 background-color="rgb(48,65,86)"
                 text-color="#fff"
                 active-text-color="#ffd04b"
                 :collapse-transition="false"
                 :collapse="isCollapse"
                 router
        >
            <div style="height: 60px;line-height: 60px;text-align: center;display: flex;justify-content: center;align-items: center;flex-direction: row">
                <img src="https://img-blog.csdnimg.cn/dbc2eaa294194339a3b1d509e6739306.png#pic_center" style="height: 45px;" alt="">
                <b style="color: #7cd9fa;margin-left: 15px;" v-show="!isCollapse">
                    <span style="color:#ffffff;font-weight: bolder">教务管理系统【管理员】</span>
                </b>
            </div>
            <el-menu-item index="/Admin/home">
                <i class="el-icon-s-home" style="color:#e8ce3d;font-weight: bolder"></i><span>主页</span>
            </el-menu-item>
            <el-submenu style="background:#e8dbdb">
                <i class="el-icon-info" style="color:#e8ce3d;font-weight: bolder"></i><span>事务</span>
                <el-menu-item  style="color:#c42d2d">
                    <i class="el-icon-s-data" style="color:#e8ce3d;font-weight: bolder"></i>
                    <span style="color:#e8ce3d;font-weight: bolder">统计分数</span>
                    <!--<el-menu-item index="1-3">选项1</el-menu-item>-->
                </el-menu-item>
                <el-menu-item >
                    <i class="el-icon-s-order" style="color:#e8ce3d;font-weight: bolder"></i>
                    <span style="color:#e8ce3d;font-weight: bolder">统计排名</span>
                </el-menu-item>
                <el-menu-item>
                    <i class="el-icon-data-analysis" style="color:#e8ce3d;font-weight: bolder"></i>
                    <span style="color:#e8ce3d;font-weight: bolder">统计挂科率</span>
                </el-menu-item>
            </el-submenu>

        </el-menu>
    </div>

</template>

<script>
export default {
    name: "AsideAdmin",
    props:{
        isCollapse:Boolean,
        sideWidth:String
    }
}
</script>

<style scoped>

</style>