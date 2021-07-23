package com.system.service;

import java.util.List;
import java.util.Map;

public interface PersonnelService {

    int entryTeacher(Map<String, Object> map);

    int entryAcademic(Map<String, Object> map);

    List<Object> getAcademic();

    int editAcademic(Map<String, Object> map);

    int delAcademic(String acid);

    List<Object> getTeacher();

    int editTeacher(Map<String, Object> map);

    int delTeacher(String tid);
}
