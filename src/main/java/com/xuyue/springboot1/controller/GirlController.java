package com.xuyue.springboot1.controller;

import com.xuyue.springboot1.domain.Girl;
import com.xuyue.springboot1.domain.Result;
import com.xuyue.springboot1.repository.GirlRepository;
import com.xuyue.springboot1.service.GirlService;
import com.xuyue.springboot1.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {
    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    //获取日志
    private static final Logger logger = LoggerFactory.getLogger(GirlController.class);

    /**
     * 查询所有女生列表
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
        return girlRepository.findAll();
    }

    /**
     * 查询一个女生
     */
    @GetMapping(value = "/girls/{id}")
    public Girl girlSelect(@PathVariable("id") int id){
        return girlRepository.getOne(id);
    }

    /**
     * 添加一个女生
     * @Valid 验证girl对象
     * bindingResult 里面可以查到错误信息
     */
    @PostMapping(value = "/girls")
    public Result girlAdd(@Valid Girl girl, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            logger.info("验证失败={}",bindingResult.getFieldError().getDefaultMessage());
            return ResultUtil.error(-2,bindingResult.getFieldError().getDefaultMessage());
        }
        Girl girl1 = new Girl();
        girl.setAge(girl.getAge());
        girl.setCupSize(girl.getCupSize());
        return ResultUtil.success(girlRepository.save(girl));
    }

    /**
     * 更新一个女生
     */
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") int id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") int age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girlRepository.save(girl);
    }

    /**
     * 删除一个女生
     */
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") int id){
        girlRepository.deleteById(id);
    }

    /**
     * 通过年龄查询(在接口中新增查询方法)
     */
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") int age){
        return girlRepository.findByAge(age);
    }

    /**
     * 添加两个女生(事物管理)
     */
    @PostMapping(value = "/girls/tow")
    public void girlAddTow(){
        girlService.girlAddTow();
    }

    /**
     * 根据id获取某个女生的年龄，
     * <10 岁 返回“你还在上小学吧”
     * 10-16 返回 “你可能还在上初中”
     */
    @GetMapping(value = "/girls/getAge/{id}")
    public void getAge(@PathVariable("id") int id) throws Exception{
        girlService.getAge(id);
    }
}
