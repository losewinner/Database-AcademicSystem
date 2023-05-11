<template>
  <el-container class = "整个页面" style = "margin-top: 10px">
      <transition name = "el-zoom-in-center">

      </transition>
  </el-container>
</template>

<script>
export default {
    name: "AdminManage",
    data(){
        return{
            //大页面置换的boolean参数
            PageChange:{
                StuPage:true,
                TeaPage:false,
                CosPage:false,
            },
            pagination:{
                pagenum:1,
                pagesize:10,
                total:0,
            },
        }
    }
}
</script>

<style scoped>

</style>