package com.system.mapper;

import com.system.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TeacherMapper {
    List<Object> getCourse(Teacher teacher);

    List<Object> getGrade(Teacher teacher);

    List<Object> getStudent(Map<String, Object> map);

    void upGrade(Map<String, Object> map);
}
