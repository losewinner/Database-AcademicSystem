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
    private Long total;
    private Object data;


    private static Result result(String code,String msg,Long total,Object data){
        return new Result(code,msg,total,data);
    }
    public static Result success(){
        return result(Constants.CODE_200,"",0L,null);
    }
    public static Result success(Object data){
        return result(Constants.CODE_200,"成功",0L,data);

    }
    public static Result success(Object data,Long total){
        return result(Constants.CODE_200,"成功",total,data);
    }

    public static Result fail(String msg,Long total,Object data){
        return result(Constants.CODE_400,msg,total,data);
    }


}
