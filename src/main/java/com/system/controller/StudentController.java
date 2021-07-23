package com.system.controller;

import com.system.pojo.Course;
import com.system.pojo.Student;
import com.system.pojo.StudentBaseInfo;
import com.system.pojo.Teacher;
import com.system.service.StudentService;
import com.system.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/student_myinfo")
    public String getInfo(HttpServletRequest req, HttpSession session) {
        Student stu = (Student) session.getAttribute("user");
        StudentBaseInfo info = studentService.getInfo(stu);
        Date birthday = info.getBirthday();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.createNow().getTime());
        int year = calendar.get(Calendar.YEAR);
        calendar.setTime(birthday);
        int old = year - calendar.get(Calendar.YEAR);
        info.setOld(old);
        req.setAttribute("info", info);
        return "student/myinfo";
    }

    @GetMapping("/getCourse")
    public void getCourse(HttpSession session, HttpServletResponse resp) throws IOException {
        Student stu = (Student) session.getAttribute("user");
        List<Object> course = studentService.getCourse(stu);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(JsonData.toString(course, "course"));
    }

    @GetMapping("/judgeCourse")
    public void judgeCourse(@RequestParam Map<String, Object> map, HttpSession session, HttpServletResponse resp) throws IOException {
        Student stu = (Student) session.getAttribute("user");
        map.put("sid", stu.getSid());
        if (!studentService.judgeCourse(map)) {
            studentService.selectCourse(map);
            resp.getWriter().write("true");
        } else resp.getWriter().write("false");
    }

    @GetMapping("/getElectiveCourse")
    public void getElectiveCourse(HttpSession session, HttpServletResponse resp) throws IOException {
        Student stu = (Student) session.getAttribute("user");
        List<Object> electiveCourse = studentService.getElectiveCourse(stu);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(JsonData.toString(electiveCourse, "electiveCourse"));
    }

    @GetMapping("/judgeElectiveCourse")
    public void judgeElectiveCourse(@RequestParam Map<String, Object> map, HttpSession session, HttpServletResponse resp) throws IOException {
        Student stu = (Student) session.getAttribute("user");
        map.put("sid", stu.getSid());
        if (studentService.judgeCourse(map)) {
            studentService.dropElectiveCourse(map);
            resp.getWriter().write("true");
        } else resp.getWriter().write("false");
    }


    @GetMapping("/getSelectedCourse")
    public void getSelectedCourse(HttpSession session, HttpServletResponse resp) throws IOException {
        Student stu = (Student) session.getAttribute("user");
        List<Object> course = studentService.getSelectedCourse(stu);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(JsonData.toString(course, "course"));
    }

    @GetMapping("/getGrade")
    public void getGrade(HttpSession session, HttpServletResponse resp) throws IOException {
        Student stu = (Student) session.getAttribute("user");
        List<Object> course = studentService.getGrade(stu);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(JsonData.toString(course, "course"));
    }


    @GetMapping("/getTeacherName")
    public void getTeacherName(HttpServletResponse resp) throws IOException {

        List<Object> teacherName = studentService.getTeacherName();
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(JsonData.toString(teacherName, "teacherName"));
    }

    @GetMapping("/getTeacherTid")
    public void getTeacherTid(String tname, HttpServletResponse resp) throws IOException {
        List<Object> teacherTid = studentService.getTeacherTid(tname);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(JsonData.toString(teacherTid, "teacherTid"));
    }

    @GetMapping("/searchTeacher")
    public void searchTeacher(String tid, HttpServletResponse resp, HttpServletRequest req) throws IOException {
        Teacher teacher = studentService.searchTeacher(tid);

        int i = 0;
        String[] cnames;
        List<Object> course = studentService.teacherCourse(teacher);
        Set<Object> set = new LinkedHashSet<>();

        for (Object o : course) {
            set.add(((Course) o).getCname());
        }
        cnames = new String[set.size()];
        for (Object o : set) {
            cnames[i] = (String) o;
            i++;
        }
        req.setAttribute("cnames", cnames);

        teacher.setCourse(cnames);

        List<Object> teacherInfo = new LinkedList<>();
        teacherInfo.add(teacher);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(JsonData.toString(teacherInfo, "teacherInfo"));
    }
}
