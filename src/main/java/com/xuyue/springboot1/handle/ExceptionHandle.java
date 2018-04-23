package com.xuyue.springboot1.handle;

import com.xuyue.springboot1.domain.Result;
import com.xuyue.springboot1.exception.ProjectException;
import com.xuyue.springboot1.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class ExceptionHandle {
    //获取日志
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        //该异常是哪种异常(系统默认的Exception 或者 项目指定的ProjectException)
        if (e instanceof ProjectException) {
            ProjectException projectException = (ProjectException) e;
            return ResultUtil.error(projectException.getCode(), projectException.getMessage());
        } else {
            //将系统异常进行打印
            logger.info("【系统异常】",e);
            return ResultUtil.error(-1, "未知错误");
        }
    }
}
