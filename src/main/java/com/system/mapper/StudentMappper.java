package com.system.mapper;

import com.system.pojo.Student;
import com.system.pojo.StudentBaseInfo;
import com.system.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface StudentMappper {
    StudentBaseInfo getInfo(Student student);

    List<Object> getCourse(Student student);

    List<Object> getSelectedCourse(Student student);

    List<Object> getGrade(Student student);

    boolean judgeCourse(Map<String, Object> map);

    void selectCourse(Map<String, Object> map);

    Teacher searchTeacher(String tid);

    List<Object> teacherCourse(Teacher teacher);

    List<Object> getTeacherName();

    List<Object> getTeacherId(String tname);

    List<Object> getElectiveCourse(Student student);

    void dropElectiveCourse(Map<String, Object> map);
}
