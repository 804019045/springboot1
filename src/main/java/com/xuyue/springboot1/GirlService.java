package com.xuyue.springboot1;

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
}
