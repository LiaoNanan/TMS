package com.system.service;

import com.system.pojo.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    List<Object> getCourse(Teacher teacher);

    List<Object> getGrade(Teacher teacher);

    List<Object> getStudent(Map<String, Object> map);

    void upGrade(Map<String, Object> map);
}
