package com.system.service;

import com.system.pojo.Student;
import com.system.pojo.StudentBaseInfo;
import com.system.pojo.Teacher;

import java.util.List;
import java.util.Map;


public interface StudentService {
    StudentBaseInfo getInfo(Student student);

    List<Object> getCourse(Student student);

    List<Object> getSelectedCourse(Student student);

    List<Object> getGrade(Student student);

    boolean judgeCourse(Map<String, Object> map);

    void selectCourse(Map<String, Object> map);


    List<Object> getTeacherName();

    List<Object> getTeacherTid(String tname);

    Teacher searchTeacher(String tid);

    List<Object> teacherCourse(Teacher teacher);

    List<Object> getElectiveCourse(Student student);

    void dropElectiveCourse(Map<String, Object> map);
}
