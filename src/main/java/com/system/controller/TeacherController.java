package com.system.controller;

import com.system.pojo.Course;
import com.system.pojo.Teacher;
import com.system.service.TeacherService;
import com.system.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @RequestMapping("/getCourse")
    public void getCourse(HttpSession session, HttpServletResponse resp) throws IOException {
        Teacher user = (Teacher) session.getAttribute("user");
        List<Object> course = teacherService.getCourse(user);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(JsonData.toString(course, "course"));
    }

    @RequestMapping("/getGrade")
    public void getGrade(String sid, HttpSession session, HttpServletResponse resp) throws IOException {
        Teacher user = (Teacher) session.getAttribute("user");
        String tid = user.getTid();
        user.setTid(sid);

        List<Object> grade = teacherService.getGrade(user);
        user.setTid(tid);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(JsonData.toString(grade, "grade"));
    }

    @RequestMapping("/teacher-information")
    public String getTeacherCourse(HttpSession session, HttpServletRequest req) {
        int i = 0;
        String[] cnames;
        Teacher user = (Teacher) session.getAttribute("user");

        List<Object> course = teacherService.getCourse(user);
        Set<Object> set = new LinkedHashSet<>();
        cnames = new String[course.size()];
        for (Object o : course) {
            set.add(((Course) o).getCname());
        }
        for (Object o : set) {
            cnames[i] = (String) o;
            i++;
        }
        req.setAttribute("cnames", cnames);
        return "teacher/teacher-information";
    }

    @RequestMapping("/getStudent")
    public void getStudent(@RequestParam Map<String, Object> map, HttpSession session, HttpServletResponse resp) throws IOException {
        Teacher user = (Teacher) session.getAttribute("user");
        map.put("tid", user.getTid());
        List<Object> student = teacherService.getStudent(map);

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(JsonData.toString(student, "student"));
    }

    @RequestMapping("/upGrade")
    @ResponseBody
    public void upGrade(@RequestParam Map<String, Object> map, HttpSession session) {
        Teacher user = (Teacher) session.getAttribute("user");
        map.put("tid", user.getTid());
        teacherService.upGrade(map);
    }
}
