<template>
    <el-container style="display: flex;flex-direction: column;margin-top:15px">
        <el-container el-container style="height: 20px; margin-top:0px">
            <el-select v-model="selectSemester" placeholder="请选择学期" style="width:30%;margin-right: 5px">
                <el-option v-for="item in optionSemester"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
            </el-select>
            <el-button @click="searchClick()" type="primary" icon="el-icon-search" style="height: 40px;margin-right: 10px">搜索
            </el-button>
        </el-container>
        <el-container style="display: flex;flex-direction: column;margin-top:15px">
            <div style="margin-top: 20px">
                <table>
                    <thead>
                    <tr>
                        <th>&nbsp;</th>
                        <th>周一</th>
                        <th>周二</th>
                        <th>周三</th>
                        <th>周四</th>
                        <th>周五</th>
                        <th>周六</th>
                        <th>周日</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="i in 12" :key="i">
                        <td>{{ i }}</td>
                        <td v-for="j in 7" :key="(i - 1) * 7 + j" :id="getTdId(i, j)" style="width: 120px">&nbsp;</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </el-container>
    </el-container>
</template>

<script>
import axios from "axios";

export default {
    name: "CheckTimetable",
    data(){
        //如果data里面什么都不写，页签跳转会出问题！
        return {
            selectedRowIndexes:[],
            maxOptionValue:1,
            optionSemester: [], //后端导入学期表，获得学期
            selectSemester: '',
            FromDbInfo: [],
            colorNum:0
        }
    },
    methods:{
        reset(){
            this.FromDbInfo=[];
            this.colorNum=0;
            for(var i=1;i<=12;i++)
            {
                for(var j=1;j<=7;j++)
                {
                    let td = document.getElementById(this.getTdId(i, j));
                    td.textContent = ""; // 清空单元格内容
                    for(var k=0;k<=9;k++) {
                        td.classList.remove("course" + k);
                    }
                    td.style.border = "1px solid black"; // 还原边框样式
                }
            }
        },
        mounted() {
            let course = this.FromDbInfo[0].courseName;
            this.FromDbInfo.forEach((item) => {
                if(item.courseName != course)
                {
                    this.colorNum+=1;
                    if(this.colorNum>9) this.colorNum=0;
                    course=item.courseName;
                }
                let [day, start, end] = this.parseClassTime(item.classTime); // 解析上课时间
                let td = document.getElementById(this.getTdId(start, day)); // 获取对应的td单元格
                console.log("td", td);
                let str = item.courseName;
                console.log("长度", str.length);
                if(str.length<6)
                {
                    td.innerText = str; // 在单元格中显示课程名称
                    str='';
                }
                else
                {
                    td.innerText = str.slice(0,6);
                    str = str.slice(6);
                }
                td.classList.add("course"+this.colorNum); // 添加样式以区分课程单元格和普通单元格
                td.style.border = "0px";
                for(var i = start+1; i<=end ;i++)
                {
                    let td = document.getElementById(this.getTdId(i, day)); // 获取对应的td单元格
                    if(str.length>0) {
                        if (str.length < 6) {
                            td.innerText = str; // 在单元格中显示课程名称
                            str = '';
                        } else {
                            td.innerText = str.slice(0, 6);
                            str = str.slice(6);
                        }
                    }
                    td.classList.add("course"+this.colorNum); // 添加样式以区分课程单元格和普通单元格
                    td.style.border = "0px";
                }
            });
        },
        parseClassTime(classTime) {
            // 解析上课时间，返回[day, start, end]数组
            let day = this.parseDay(classTime);
            let [start, end] = classTime.slice(2).split("-");
            return [day, parseInt(start), parseInt(end)];
        },
        parseDay(classTime) {
            // 解析上课时间中的星期几，返回0-6表示周一至周日
            let dayStr = classTime.match(/周(.)\d+-\d+/)[1];
            let dayMap = new Map([
                ["一", 1],
                ["二", 2],
                ["三", 3],
                ["四", 4],
                ["五", 5],
                ["六", 6],
                ["日", 7],
            ]);
            return dayMap.get(dayStr);
        },
        getTdId(row, col) {
            // 根据行列获取对应的td单元格的id
            return "td-" + row + "-" + col;
        },
        getClassTime(data) {
            this.reset();
            for(const item of data){
                let str = item.classTime;
                let results = str.split(","); // 使用逗号拆分字符串，返回数组
                for(const time of results)
                {
                    this.FromDbInfo.push({courseName:item.courseName,classTime:time});
                }
            }
            console.log("！！！", this.FromDbInfo);
            this.mounted();
        },
        searchClick(){
            //搜之前做判断，如果学期没选，就不做搜索，提醒用户必须选择学期
            console.log("wtf",this.selectSemester)
            if(this.selectSemester===''){
                this.$alert('请选择学期！', '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$message({
                            type: 'info',
                            message: `信息: ${ action }`
                        });
                    }
                });
            }
            //然后获取对应的数据
            //解析所选的学期
            var semester_dict = this.optionSemester.find(x=>x.value===this.selectSemester);
            var semester = semester_dict.label;
            //按照学生和课程的输入来选择
            console.log("semester ",semester);
            axios.post("/selectcourse/getAllStuCourse?semester="+semester+"&studentId="+localStorage.getItem("userid")).then(res=>res.data).then(res=>{
                console.log("？？？",res.data);
                this.getClassTime(res.data);
                    this.$message({
                        type: 'success',
                        message: `查找成功！`
                    })
            })
        },
        loadData(){
            //向数据库请求数据，涉及：学期表
            this.maxOptionValue = 1;
            this.optionSemester = [];
            console.log("w");
            //获取学期信息，放进optionSemester中
            var that = this;
            axios.get("/semestatus/list").then(res=>{
                console.log("yeyeye",res.data);    //获取成功
                for(const item of res.data)
                {
                    let newDict={};
                    newDict['value'] = '选项'+that.maxOptionValue;
                    newDict['label'] = item.semester;
                    that.optionSemester.push(newDict);
                    that.maxOptionValue+=1;
                }
                console.log('wwwwwww',that.optionSemester);
            })
        },
    },
    created(){
        this.loadData();
    }
}
</script>

<style scoped>
table {
    border-collapse: collapse;
}

th,
td {
    border: 1px solid black;
    padding: 10px;
    text-align: center;
}

.course0 {
    background-color: royalblue;
}
.course1 {
    background-color: orangered;
}
.course2 {
    background-color: dodgerblue;
}
.course3 {
    background-color: brown;
}
.course4 {
    background-color: lightseagreen;
}
.course5 {
    background-color: greenyellow;
}
.course6 {
    background-color: mediumpurple;
}
.course7 {
    background-color: yellowgreen;
}
.course8 {
    background-color: forestgreen;
}
.course9 {
    background-color: red;
}
</style>
