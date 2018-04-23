package com.xuyue.springboot1.util;

import com.xuyue.springboot1.domain.Result;

public class ResultUtil {

    /**
     * 成功
     */
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(1);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    /**
     * 没有参数的成功
     */
    public static Result success(){
        return  success(null);
    }

    /**
     * 错误
     */
    public static Result error(int code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
