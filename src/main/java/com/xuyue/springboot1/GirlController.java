package com.xuyue.springboot1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GirlController {
    @Autowired
    private GirlRepository girlRepository;

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
     */
    @PostMapping(value = "/girls")
    public Girl girlAdd(@RequestParam("cupSize") String cupSize,
                        @RequestParam("age") int age){
        Girl girl = new Girl();
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girlRepository.save(girl);
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
    @Autowired
    private GirlService girlService;

    @PostMapping(value = "/girls/tow")
    public void girlAddTow(){
        girlService.girlAddTow();
    }
}
