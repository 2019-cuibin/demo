package com.example.demo.service;

import com.example.demo.entity.User;


import java.util.List;
import java.util.Map;

/**
 * @author: cuibin
 * @Date: 2019/9/17 09:58
 * @Description:
 */
public interface UserService {
    /**
     * 验证登录
     * @param user
     * @return
     */
    boolean login(User user);

    /**
     * 查询所有信息
     * @return
     */
    List<User> selectAll();

    /**
     * 查询数据的总条数
     * @return
     */
    int allCount();

    /**
     * 根据条件进行分页查询
     * @param map
     * @return
     */
    List<Map> selectByPage(Map map);
}
