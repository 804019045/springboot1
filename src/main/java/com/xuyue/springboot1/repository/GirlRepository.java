package com.xuyue.springboot1.repository;

import com.xuyue.springboot1.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GirlRepository extends JpaRepository<Girl,Integer>{
    /**
     * 通过年龄查询
     * 方法名不能乱写，一定要写成findByAge
     */
    public List<Girl> findByAge(int age);
}
