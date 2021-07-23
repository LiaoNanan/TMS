package com.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AcademicMapper {
    List<Object> getCourse();

    void upCourse(Map<String, Object> map);

    Map<String, Object> getCourseOne(Map<String, Object> map);

    Map<String, Object> getId(Map<String, Object> map);

    void delCourse(Map<String, Object> map);

    Boolean judgeStu(Map<String, Object> map);

    void insertStu(Map<String, Object> map);

    Map<String, Object> getMid(Map<String, Object> map);

    void release(Map<String, Object> map);

    void releaseChoose(Map<String, Object> map);

    List<Object> toRelease();

    Map<String, Object> getTid(Map<String, Object> map);

    Map<String, Object> getName(Map<String, Object> map);

    List<Object> getStudent(Map<String, Object> map);

    List<Object> getChooseStu(Map<String, Object> map);

    void changeGrade(Map<String, Object> map);

    void insertCourse(Map<String, Object> map);

}
