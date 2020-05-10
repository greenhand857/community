package com.nowcoder.community;


import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaServie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest
// 测试代码期望使用正式运行的类使用相同的配置类
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

	//如何创建Spring容器的变量？创建Spring容器的成员变量
	private ApplicationContext applicationContext;
	// 程序运行时 applicationContext自动传进来做记录，可以通过成员变量做记录并使用
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);

		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());

		alphaDao = applicationContext.getBean("alphaHibernate",AlphaDao.class);
		System.out.println(alphaDao.select());
	}

	@Test
	//测试Bean管理方式
	public void testBeanManagement(){
		//首先希望能够通过容器获取Service
		AlphaServie alphaServie = applicationContext.getBean(AlphaServie.class);
		System.out.println(alphaServie);

		alphaServie = applicationContext.getBean(AlphaServie.class);
		System.out.println(alphaServie);
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	//假如当前Bean希望使用AlphaDao，我们不需要通过容器去get获取，我们只要声明给当前Beann注入AlphaDao
	@Autowired
	@Qualifier("alphaHibernate")
	//Spring容器把AlphaDao注入给这个属性
	private AlphaDao alphaDao;
	@Autowired
	private AlphaServie alphaServie;
	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void testDI(){
		System.out.println(alphaDao);
		System.out.println(alphaServie);
		System.out.println(simpleDateFormat);
	}

}
