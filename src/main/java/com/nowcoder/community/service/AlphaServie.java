package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")
//每次 访问BEAN的时候都会创建一个对象
public class AlphaServie {

    //我们希望通过service调用Dao，使用依赖注入
    @Autowired
    private AlphaDao alphaDao;

    public String find(){
        return alphaDao.select();
    }



    public AlphaServie(){
        System.out.println("实例化");
    }
    //该注释表示这个方法会在构造器之后调用
    @PostConstruct
    public void init(){
        System.out.println("初始化AlphaService");
    }
    //该注释表示在销毁对象前调用该方法
    @PreDestroy
    public void destroy(){
        System.out.println("销毁AlphaService");
    }
}
