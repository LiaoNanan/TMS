package com.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PersonnelMapper {

    int entryTeacher(Map<String, Object> map);

    int entryAcademic(Map<String, Object> map);

    List<Object> getAcademic();

    int editAcademic(Map<String, Object> map);

    int delAcademic(String acid);

    List<Object> getTeacher();

    int editTeacher(Map<String, Object> map);

    int delTeacher(String tid);
}
