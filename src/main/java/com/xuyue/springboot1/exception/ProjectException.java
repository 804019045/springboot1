package com.xuyue.springboot1.exception;

import com.xuyue.springboot1.enums.ResultEnum;

/**
 * 注意，这里继承的时候不要继承Exception
 * Spring的事物回滚只针对RuntimeException
 */
public class ProjectException extends RuntimeException {
    private int code;

    public ProjectException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
