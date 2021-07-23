package com.system.service;

import com.system.mapper.UserMapper;
import com.system.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String id) {
        return userMapper.login(id);
    }


    @Override
    public User judgeId(String id) {
        return userMapper.judgeId(id);
    }

    @Override
    public void changePwd(User user) {
        userMapper.changePwd(user);
    }

    @Override
    public void changeEmail(User user) {
        userMapper.changeEmail(user);
    }

    @Override
    public Student studentUser(String id) {
        return userMapper.studentUser(id);
    }

    @Override
    public Personnel personnelUser(String id) {
        return userMapper.personnelUser(id);
    }

    @Override
    public Teacher teacherUser(String id) {
        return userMapper.teacherUser(id);
    }

    @Override
    public Academic academicUser(String id) {
        return userMapper.academicUser(id);
    }

}
