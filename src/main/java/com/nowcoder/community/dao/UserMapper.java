package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//专门给Mybatis识别Spring容器注入使用的注释
public interface UserMapper {
    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    //增加一个用户，返回插入数据的行数
    int insertUser(User user);
    //返回修改了几行状态
    int updateStatus(int id,int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id,String password);
}
