package com.system.service;

import com.system.mapper.AcademicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AcademicServiceImpl implements AcademicService {
    @Autowired
    AcademicMapper academicMapper;

    @Override
    public Map<String, Object> getName(Map<String, Object> map) {
        return academicMapper.getName(map);
    }

    @Override
    public List<Object> getCourse() {
        return academicMapper.getCourse();
    }

    @Override
    public void upCourse(Map<String, Object> map) {
        academicMapper.upCourse(map);
    }

    @Override
    public Map<String, Object> getCourseOne(Map<String, Object> map) {
        return academicMapper.getCourseOne(map);
    }

    @Override
    public Map<String, Object> getId(Map<String, Object> map) {
        return academicMapper.getId(map);
    }

    @Override
    public void delCourse(Map<String, Object> map) {
        academicMapper.delCourse(map);
    }

    @Override
    public Boolean judgeStu(Map<String, Object> map) {
        return academicMapper.judgeStu(map);
    }

    @Override
    public void insertStu(Map<String, Object> map) {
        academicMapper.insertStu(map);
    }

    public Map<String, Object> getMid(Map<String, Object> map) {
        return academicMapper.getMid(map);
    }

    @Override
    public void release(Map<String, Object> map) {
        academicMapper.release(map);
    }

    @Override
    public List<Object> toRelease() {
        return academicMapper.toRelease();
    }

    @Override
    public void releaseChoose(Map<String, Object> map) {
        academicMapper.releaseChoose(map);
    }

    @Override
    public Map<String, Object> getTid(Map<String, Object> map) {
        return academicMapper.getTid(map);
    }

    @Override
    public List<Object> getStudent(Map<String, Object> map) {
        return academicMapper.getStudent(map);
    }

    @Override
    public void changeGrade(Map<String, Object> map) {
        academicMapper.changeGrade(map);
    }

    @Override
    public void insertCourse(Map<String, Object> map) {
        academicMapper.insertCourse(map);
    }

    public List<Object> getChooseStu(Map<String, Object> map) {
        return academicMapper.getChooseStu(map);
    }
}
