package com.system.mapper;

import com.system.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    User login(String id);

    User judgeId(String id);

    void changePwd(User user);

    void changeEmail(User user);

    Student studentUser(String id);

    Personnel personnelUser(String id);

    Teacher teacherUser(String id);

    Academic academicUser(String id);

}
