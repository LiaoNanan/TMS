package com.system.service;

import com.system.mapper.StudentMappper;
import com.system.pojo.Student;
import com.system.pojo.StudentBaseInfo;
import com.system.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMappper studentMappper;

    @Override
    public StudentBaseInfo getInfo(Student student) {
        return studentMappper.getInfo(student);
    }

    @Override
    public List<Object> getCourse(Student student) {
        return studentMappper.getCourse(student);
    }

    @Override
    public List<Object> getSelectedCourse(Student student) {
        return studentMappper.getSelectedCourse(student);
    }

    @Override
    public List<Object> getGrade(Student student) {
        return studentMappper.getGrade(student);
    }

    @Override
    public boolean judgeCourse(Map<String, Object> map) {
        return studentMappper.judgeCourse(map);
    }

    @Override
    public void selectCourse(Map<String, Object> map) {
        studentMappper.selectCourse(map);
    }

    @Override
    public List<Object> getTeacherName() {
        return studentMappper.getTeacherName();
    }

    @Override
    public List<Object> getTeacherTid(String tname) {
        return studentMappper.getTeacherId(tname);
    }

    @Override
    public Teacher searchTeacher(String tid) {
        return studentMappper.searchTeacher(tid);
    }

    @Override
    public List<Object> teacherCourse(Teacher teacher) {
        return studentMappper.teacherCourse(teacher);
    }

    @Override
    public List<Object> getElectiveCourse(Student student) {
        return studentMappper.getElectiveCourse(student);
    }

    @Override
    public void dropElectiveCourse(Map<String, Object> map) {
        studentMappper.dropElectiveCourse(map);
    }


}
