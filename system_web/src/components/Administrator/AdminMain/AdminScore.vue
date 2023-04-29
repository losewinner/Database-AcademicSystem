<template>
<div>{{use}}
</div>
</template>

<script>
export default {
    name: "AdminScore",
    data(){
        //如果data里面什么都不写，页签跳转会出问题！
        return{
            use:"losewinner"
        }
    }
}
</script>

<style scoped>

</style>