package com.nowcoder.community;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
// 测试代码期望使用正式运行的类使用相同的配置类
@ContextConfiguration(classes = CommunityApplication.class)
public class LoggerTest {
    //要记日志，首先需要实例化日志组件，就是Logger接口，每个类记录日志都单独为这个类实例化一个logger
    private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void testLogger(){
        System.out.println(logger.getName());

        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");


    }
}
