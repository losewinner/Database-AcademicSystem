package com.example.academic_affairs_management_system.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口统一返回包装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String code;
    private String msg;
    private int total;
    private Object data;


    private static Result result(String code,String msg,int total,Object data){
        return new Result(code,msg,total,data);
    }
    public static Result success(){
        return result(Constants.CODE_200,"成功",0,null);
    }
    public static Result success(Object data){
        return result(Constants.CODE_200,"成功",0,data);

    }
    public static Result success(Object data,int total){
        return result(Constants.CODE_200,"成功",total,data);
    }

    public static Result fail(String msg){
        return result(Constants.CODE_400,msg,0,null);
    }


}
