package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author: cuibin
 * @Date: 2019/9/17 09:57
 * @Description:
 */
@Mapper
public interface UserDao{
    /**
     * 登录
     * @param user
     * @return
     */
    @Select("select count(*) from user where username=#{username} and password=#{password}")
    int login(User user);

    /**
     * 查询所有信息
     * @return
     */
    @Select("select * from user")
    List<User> selectAll();

    /**
     * 查询数据的总条数
     * @return
     */
    @Select("select count(*) from user")
    int allCount();

    /**
     * 根据条件进行分页查询
     * @param map
     * @return
     */
    @Select("select * from user limit #{pageNo},#{pageSize}")
    List<Map> selectByPage(Map map);
}
