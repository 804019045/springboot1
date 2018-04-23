package com.xuyue.springboot1.service;

import com.xuyue.springboot1.domain.Girl;
import com.xuyue.springboot1.enums.ResultEnum;
import com.xuyue.springboot1.exception.ProjectException;
import com.xuyue.springboot1.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    /**
     * 添加两个女生,如果一个女生添加失败,另一个女生也不要添加
     * @Transactional 的作用就是事物管理
     * 除了查询操作，其他的三种操作都需要加上事物
     */
    @Transactional
    public void girlAddTow(){
        Girl girl1 = new Girl();
        girl1.setCupSize("A");
        girl1.setAge(20);
        girlRepository.save(girl1);

        Girl girl2 = new Girl();
        girl2.setCupSize("BBB");
        girl2.setAge(21);
        girlRepository.save(girl2);
    }

    /**
     * 根据id获取某个女生的年龄，
     * <10 岁 返回“你还在上小学吧”
     * 10-16 返回 “你可能还在上初中”
     */
    public void getAge(int id) throws Exception {
        Girl girl = girlRepository.getOne(id);
        int age = girl.getAge();
        if(age <10){
            /**
             * 这里如果不要特定的code，用默认的Exception即可,必须传一个字符串信息，否则会报系统错误
             * throw new Exception("你还在上小学吧！");
             * 如果要指定的code,默认错误是0，这里如果需要100，就用项目指定的异常
             * throw new ProjectException(100,"你还在上小学吧");
             */
            throw new ProjectException(ResultEnum.PRIMARY_SCHOOL);
        }else if (age >= 10 && age < 16){
            throw new ProjectException(ResultEnum.MIDDLE_SCHOOL);
        }
    }
}
