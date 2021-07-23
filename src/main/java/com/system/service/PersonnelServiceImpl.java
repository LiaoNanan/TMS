package com.system.service;

import com.system.mapper.PersonnelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonnelServiceImpl implements PersonnelService {
    @Autowired
    PersonnelMapper personnelMapper;

    @Override
    public int entryTeacher(Map<String, Object> map) {
        return personnelMapper.entryTeacher(map);
    }

    @Override
    public int entryAcademic(Map<String, Object> map) {
        return personnelMapper.entryAcademic(map);
    }

    @Override
    public List<Object> getAcademic() {
        return personnelMapper.getAcademic();
    }

    @Override
    public int editAcademic(Map<String, Object> map) {
        return personnelMapper.editAcademic(map);
    }

    @Override
    public int delAcademic(String acid) {
        return personnelMapper.delAcademic(acid);
    }

    @Override
    public List<Object> getTeacher() {
        return personnelMapper.getTeacher();
    }

    @Override
    public int editTeacher(Map<String, Object> map) {
        return personnelMapper.editTeacher(map);
    }

    @Override
    public int delTeacher(String tid) {
        return personnelMapper.delTeacher(tid);
    }


}
