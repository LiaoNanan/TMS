package com.system.service;

import com.system.mapper.TeacherMapper;
import com.system.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public List<Object> getCourse(Teacher teacher) {
        return teacherMapper.getCourse(teacher);
    }

    public List<Object> getGrade(Teacher teacher) {
        return teacherMapper.getGrade(teacher);
    }

    public List<Object> getStudent(Map<String, Object> map) {
        return teacherMapper.getStudent(map);
    }

    @Override
    public void upGrade(Map<String, Object> map) {
        teacherMapper.upGrade(map);
    }
}
