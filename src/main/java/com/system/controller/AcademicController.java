package com.system.controller;

import com.system.service.AcademicService;
import com.system.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/academic")
public class AcademicController {
    @Autowired
    AcademicService academicService;

    @GetMapping("/getCourse")
    public void getCourse(HttpServletResponse resp) throws IOException {
        List<Object> course = academicService.getCourse();
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(JsonData.toString(course, "course"));
    }

    @GetMapping("/upCourse")
    @ResponseBody
    public void upCourse(@RequestParam Map<String, Object> map, HttpSession session, HttpServletResponse resp) throws IOException {
        int t;
        Map<String, Object> course = (Map<String, Object>) session.getAttribute("course");
        t = Integer.parseInt((String) map.get("period"));
        map.put("period", t);
        t = Integer.parseInt((String) map.get("credits"));
        map.put("credits", t);
        int judge = 1;
        if (map.get("ctype").equals("选修课")) {
            map.put("classId", 101);
        } else {
            Map<String, Object> id = academicService.getId(map);
            map.put("mid", id.get("mid"));
            map.put("classId", id.get("classId"));
        }

        map.put("oldTid", course.get("tid"));
        map.put("oldCno", course.get("cno"));
        map.put("oldClassId", course.get("classId"));
        if (map.get("ctype").equals("必修课")) {
            map.put("is_elective", 0);
        } else {
            map.put("is_elective", 1);
        }

        try {
            academicService.upCourse(map);

        } catch (Exception e) {
            judge = 0;
            resp.getWriter().write("false");
        }
        if (judge == 1) resp.getWriter().write("true");
    }

    @GetMapping("/delCourse")
    public void exitEdit(@RequestParam Map<String, Object> map, HttpSession session) {
        if (map.get("ctype").equals("选修课")) {
            map.put("classId", "101");
            session.setAttribute("course", map);
        } else {
            Map<String, Object> id = academicService.getId(map);
            map.put("classId", id.get("classId"));
            map.put("mid", id.get("mid"));
        }
        academicService.delCourse(map);
    }


    @GetMapping("/getId")
    public void getId(@RequestParam Map<String, Object> map, HttpSession session, HttpServletResponse resp) throws IOException {
        Map<String, Object> id = academicService.getMid(map);
        if (id == null || id.get("mid") == null || id.get("class_id") == null || id.get("tid") == null) {
            resp.getWriter().write("false");
        } else {
            session.setAttribute("id", id);
            resp.getWriter().write("true");
        }
    }

    @GetMapping("/insertCourse")
    @ResponseBody
    public void insertCourse(@RequestParam Map<String, Object> map, HttpSession session, HttpServletResponse resp) throws IOException {
        int t;
        int judge = 1;
        Map<String, Object> id = (Map<String, Object>) session.getAttribute("id");
        map.put("tid", id.get("tid"));
        map.put("classId", id.get("class_id"));
        map.put("mid", id.get("mid"));
        t = Integer.parseInt((String) map.get("period"));
        map.put("period", t);
        t = Integer.parseInt((String) map.get("credits"));
        map.put("credits", t);
        if (map.get("ctype").equals("选修课")) {
            map.put("is_elective", 1);
        } else {
            map.put("is_elective", 0);
        }

        try {
            academicService.insertCourse(map);
        } catch (Exception e) {
            judge = 0;
            resp.getWriter().write("false");
        }
        if (judge == 1) resp.getWriter().write("true");
    }

    @GetMapping("/judgeCourse")
    public void judgeCourse(@RequestParam Map<String, Object> map, HttpSession session, HttpServletResponse resp) throws IOException {
        Map<String, Object> courseOne = academicService.getCourseOne(map);
        Map<String, Object> course = (Map<String, Object>) session.getAttribute("course");
        if (courseOne == null) {
            resp.getWriter().write("true");
        } else {
            if ((int) (courseOne.get("is_elective")) == 1) {
                if (courseOne.get("cno").equals(course.get("cno"))) {

                }
            } else if (courseOne.get("cid").equals(course.get("cid")) && courseOne.get("mid").equals(course.get("mid")) && courseOne.get("ClassId").equals(course.get("classId"))) {
                resp.getWriter().write("true");
            }
            resp.getWriter().write("false");
        }
    }

    @GetMapping("/getCourseOne")
    @ResponseBody
    public void getCourseOne(@RequestParam Map<String, Object> map, HttpSession session) {
        if (map.get("ctype").equals("选修课")) {
            map.put("classId", "101");
            session.setAttribute("course", map);
        } else {
            Map<String, Object> id = academicService.getId(map);
            map.put("classId", id.get("classId"));
            map.put("mid", id.get("mid"));
            session.setAttribute("course", map);
        }
    }

    @GetMapping("/judgeStu")
    public void judgeStu(@RequestParam Map<String, Object> map, HttpServletResponse resp) throws IOException {
        if (!academicService.judgeStu(map)) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }

    }

    @GetMapping("/insertStu")
    public void insertStu(@RequestParam Map<String, Object> map, HttpSession session) {
        if (map.get("ssex").equals("男")) map.put("ssex", "1");
        else map.put("ssex", "0");

        String birthday = (String) map.get("idCard");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(birthday.substring(6, 10)), Integer.parseInt(birthday.substring(10, 12)) - 1, Integer.parseInt(birthday.substring(12, 14)));
        Date b = calendar.getTime();
        map.put("birthday", b);
        Map<String, Object> mid = (Map<String, Object>) session.getAttribute("mid");
        map.put("mid", mid.get("mid"));
        map.put("classId", mid.get("classId"));
        academicService.insertStu(map);

    }

    @GetMapping("/getTid")
    public void getTid(@RequestParam Map<String, Object> map, HttpSession session, HttpServletResponse resp) throws IOException {
        if (academicService.getId(map) == null) {
            resp.getWriter().write("false");
        } else {
            session.setAttribute("mid", academicService.getId(map));
            resp.getWriter().write("true");
        }
    }

    @GetMapping("/toRelease")
    public void toRelease(HttpServletResponse resp) throws IOException {
        List<Object> objects = academicService.toRelease();
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(JsonData.toString(objects, "course"));
    }

    @GetMapping("/release")
    public void release(@RequestParam Map<String, Object> map) throws IOException {
        if (map.get("classId").equals("101")) {
            academicService.releaseChoose(map);
        } else {
            academicService.release(map);
        }
        academicService.release(map);
    }

    @GetMapping("/getStudent")
    public void getStudent(@RequestParam Map<String, Object> map, HttpServletResponse resp) throws IOException {
        List<Object> student = new LinkedList<>();
        if (map.get("class_id").equals("101")) {
            student = academicService.getChooseStu(map);
        } else {
            student = academicService.getStudent(map);
        }
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write(JsonData.toString(student, "student"));
    }

    @GetMapping("/changeGrade")
    public void changeGrade(@RequestParam Map<String, Object> map) {
        academicService.changeGrade(map);
    }
}
