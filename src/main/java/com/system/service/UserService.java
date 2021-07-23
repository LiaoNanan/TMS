package com.system.service;

import com.system.pojo.*;

public interface UserService {
    User login(String id);

    User judgeId(String id);

    void changePwd(User user);

    void changeEmail(User user);

    Student studentUser(String id);

    Personnel personnelUser(String id);

    Teacher teacherUser(String id);

    Academic academicUser(String id);

}
