package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: cuibin
 * @Date: 2019/9/17 10:00
 * @Description:
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public boolean login(User user) {
        int count = userDao.login(user);
        if(count > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<User> selectAll() {
        List<User> ls = userDao.selectAll();
        return ls;
    }

    @Override
    public int allCount() {
        return userDao.allCount();
    }

    @Override
    public List<Map> selectByPage(Map map) {
        return userDao.selectByPage(map);
    }
}
