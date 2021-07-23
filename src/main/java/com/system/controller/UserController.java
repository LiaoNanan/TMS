package com.system.controller;

import com.system.mapper.UserMapper;
import com.system.pojo.User;
import com.system.service.UserService;
import com.system.util.RadomImage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    JavaMailSenderImpl sender;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/login")
    public void login(User user, HttpServletResponse resp, HttpServletRequest req, String code) throws IOException {
        boolean result;
        int error = 1;
        if (req.getSession().getAttribute("error") != null) error = (int) req.getSession().getAttribute("error") + 1;
        if (error > 3 && !code.equals(req.getSession().getAttribute("code"))) {
            req.getSession().setAttribute("msg", "验证码错误");
            req.getSession().setAttribute("error", error);
            resp.sendRedirect("/");
        } else {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getId(), user.getPassWord());
            try {
                subject.login(token);//执行登陆的方法
                User users = (User) subject.getPrincipal();
                if (users.getRole() == 1) {
                    req.getSession().setAttribute("user", userService.studentUser(user.getId()));
                    resp.sendRedirect("/student");
                } else if (users.getRole() == 2) {
                    req.getSession().setAttribute("user", userService.teacherUser(user.getId()));
                    resp.sendRedirect("/teacher");
                } else if (users.getRole() == 3) {
                    req.getSession().setAttribute("user", userService.academicUser(user.getId()));
                    resp.sendRedirect("/academic");
                } else {
                    req.getSession().setAttribute("user", userService.personnelUser(user.getId()));
                    resp.sendRedirect("/personnel");
                }
                req.getSession().removeAttribute("error");
                req.getSession().removeAttribute("msg");
            } catch (UnknownAccountException e) {//用户名不存在
                req.getSession().setAttribute("msg", "用户名错误");
                req.getSession().setAttribute("error", error);
                resp.sendRedirect("/");
            } catch (IncorrectCredentialsException e) {//密码不存在
                req.getSession().setAttribute("msg", "密码错误");
                req.getSession().setAttribute("error", error);
                resp.sendRedirect("/");
            }
        }

    }

    @GetMapping("/exit")
    public String exit(HttpSession session) {
        session.removeAttribute("User");
        return "redirect:/";
    }

    @GetMapping("/getImage")
    public void getImage(HttpServletResponse resp, HttpSession session) {
        String vericode = String.valueOf(RadomImage.getSecurityCode());

        session.setAttribute("code", vericode);
        //设置返回的内容
        resp.setContentType("img/gif");
        //浏览器不缓存响应内容--验证码图片，点一次就要刷新一次，所以不能有缓存出现
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache");
        //设置验证码失效时间
        resp.setDateHeader("Expires", 0);
        //以字节流发过去，交给img的src属性去解析即可
        try {
            ImageIO.write(new RadomImage().createImage(vericode), "gif", resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/judgeId")
    public void judgeId(HttpServletResponse resp, String id, HttpServletRequest req) throws IOException {
        HttpSession session = req.getSession();
        User user = userService.judgeId(id);
        if (user == null) resp.getWriter().write("false");
        else {
            session.setAttribute("judgeuser", user);
            resp.getWriter().write("true");
        }
    }

    @GetMapping("/judgePwd")
    public void judgePwd(HttpServletResponse resp, String id, String pwd) throws IOException {
        User user = userService.login(id);
        if (user.getPassWord().equals(pwd)) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
    }

    @GetMapping("/sendCode")
    public void sendCode(HttpServletResponse resp, HttpServletRequest req, String email) throws IOException {
        HttpSession session = req.getSession();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String mailCode = String.valueOf(RadomImage.getSecurityCode());
        if (email == null) {
            User user = (User) session.getAttribute("judgeuser");
            String oldEmail = user.getEmail();
            if (oldEmail == null || oldEmail.equals("")) {
                resp.getWriter().write("false");
            } else {
                mailMessage.setSubject("集美大学教务系统-安全中心");
                mailMessage.setText("密码重置-身份验证动态码:" + mailCode + "   30分钟内有效");
                resp.getWriter().write("true");
                session.setAttribute("mailCode", mailCode);
                mailMessage.setTo(oldEmail);
                mailMessage.setFrom("523208988@qq.com");
                sender.send(mailMessage);
            }
        } else {
            mailMessage.setSubject("集美大学教务系统-安全中心");
            mailMessage.setText("修改邮箱-身份验证动态码:" + mailCode + "   30分钟内有效");
            session.setAttribute("mailCode", mailCode);
            mailMessage.setTo(email);
            mailMessage.setFrom("523208988@qq.com");
            sender.send(mailMessage);
        }
    }

    @GetMapping("/judgeMailCode")
    public void judgeMailCode(HttpServletResponse resp, String code, HttpSession session) throws IOException {
        String vcode = (String) session.getAttribute("mailCode");
        if (vcode != null) {
            if (code.equals(vcode)) {
                resp.getWriter().write("true");
            } else {
                resp.getWriter().write("false");
            }
        } else resp.getWriter().write("false");
    }

    @GetMapping("/falseCode")
    public void falseEmail(HttpSession session) {
        session.removeAttribute("code");
    }

    @GetMapping("/changePwd")
    public void changePwd(String password, HttpSession session) {
        User user = (User) session.getAttribute("judgeuser");
        user.setPassWord(password);
        userService.changePwd(user);
    }

    @GetMapping("/changeEmail")
    public void changeEmail(String email, HttpSession session) {
        User user = (User) session.getAttribute("judgeuser");
        user.setEmail(email);
        userService.changeEmail(user);
    }

    @PostMapping("/upload")
    @ResponseBody
    public void upLoad(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String account = "yyy.jpg";
            String filePath = "D:/IDEA项目/edu-sys/target/classes/static/image";
            File dest = new File(filePath, account);
            file.transferTo(dest);
        }
    }


}
