package com.system.controller;

import com.system.service.PersonnelService;
import com.system.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/personnel")
public class PersonnelController {
    @Autowired
    PersonnelService personnelService;

    @RequestMapping("/entryTeacher")
    public void entryTeacher(@RequestParam Map<String, Object> map, HttpServletResponse resp) throws IOException {
        int judge;
        try {
            judge = personnelService.entryTeacher(map);
        } catch (Exception e) {
            judge = -1;
        } finally {
        }

        if (judge >= 1) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
    }

    @RequestMapping("/entryAcademic")
    public void entryAcademic(@RequestParam Map<String, Object> map, HttpServletResponse resp) throws IOException {
        int judge;
        try {
            judge = personnelService.entryAcademic(map);
        } catch (Exception e) {
            judge = -1;
        } finally {
        }

        if (judge >= 1) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
    }

    @RequestMapping("/getAcademic")
    public void getAcademic(HttpServletResponse resp) throws IOException {
        List<Object> academic = personnelService.getAcademic();
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(JsonData.toString(academic, "academic"));
    }

    @RequestMapping("/editAcademic")
    public void editAcademic(@RequestParam Map<String, Object> map, HttpServletResponse resp) throws IOException {
        int judge;
        try {
            judge = personnelService.editAcademic(map);
        } catch (Exception e) {
            judge = -1;
        } finally {
        }

        if (judge >= 1) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
    }

    @RequestMapping("/delAcademic")
    public void delAcademic(String acid, HttpServletResponse resp) throws IOException {
        int judge;
        try {
            judge = personnelService.delAcademic(acid);
        } catch (Exception e) {
            judge = -1;
        } finally {
        }

        if (judge >= 1) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
    }

    @RequestMapping("/getTeacher")
    public void getTeacher(HttpServletResponse resp) throws IOException {
        List<Object> academic = personnelService.getTeacher();
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(JsonData.toString(academic, "academic"));
    }

    @RequestMapping("/editTeacher")
    public void editTeacher(@RequestParam Map<String, Object> map, HttpServletResponse resp) throws IOException {
        int judge;
        try {
            judge = personnelService.editTeacher(map);
        } catch (Exception e) {
            judge = -1;
        } finally {
        }

        if (judge >= 1) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
    }

    @RequestMapping("/delTeacher")
    public void delTeacher(String tid, HttpServletResponse resp) throws IOException {
        int judge;
        try {
            judge = personnelService.delTeacher(tid);
        } catch (Exception e) {
            judge = -1;
        } finally {
        }

        if (judge >= 1) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
    }
}
