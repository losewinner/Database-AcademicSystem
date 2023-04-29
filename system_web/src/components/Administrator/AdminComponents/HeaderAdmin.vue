<template>
    <div style="display: flex;text-align: left; font-size: 12px;line-height: 60px;">
        <div style="flex:1;font-size: 18px">
            <span :class="collapseBthClass" style="cursor:pointer" @click="collapse"></span>
        </div>
        <el-dropdown style="cursor: pointer">
            <div style="display: flex; flex-direction: row; align-items: center; justify-content: center" >

                <span>欢迎！ 管理员</span><i class="el-icon-setting" style="margin-right: 5px;"></i>
            </div>
            <el-dropdown-menu>
                <span style="text-decoration: none"><el-dropdown-item>查看</el-dropdown-item></span>
                <span style="text-decoration: none"><el-dropdown-item>登出</el-dropdown-item></span>
            </el-dropdown-menu>
        </el-dropdown>

    </div>

</template>

<script>
export default {
    name: "HeaderAdmin",
    props:{
        collapseBthClass:String,
        collapse:function(){}
    },
    data(){
        return{
            user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},
        }

    },
    methods:{

    }
}
</script>

<style scoped>

</style>