import axios from "axios";
import ElementUI from "element-ui";

const request = axios.create({
    baseURL: 'http://localhost:9090',  // 注意！！ 这里是全局统一加上了 '/api' 前缀，也就是说所有接口都会加上'/api'前缀在，页面里面写接口的时候就不要加 '/api'了，否则会出现2个'/api'，类似 '/api/api/user'这样的报错，切记！！！
    timeout: 5000
})
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    //检查内存中是否有用户数据
    let user=localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{};
    if(user) {
        config.headers['token'] = user.token;  // 设置请求头
    }
    return config
}, error => {
    return Promise.reject(error)
});
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        //权限不足给出提示
        if(res.code==='401'){
            ElementUI.Message({
                message: res.msg,
                type: 'error'
            })
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)
export default request