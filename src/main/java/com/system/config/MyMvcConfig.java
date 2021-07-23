package com.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override//视图跳转
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");

        registry.addViewController("/").setViewName("login");
        registry.addViewController("/revise_password").setViewName("revise_password");
        registry.addViewController("/revise_email").setViewName("revise_email");
        registry.addViewController("/load").setViewName("load");

        registry.addViewController("/home").setViewName("common/home");
        registry.addViewController("/student").setViewName("student");
        registry.addViewController("/teacher").setViewName("teacher");
        registry.addViewController("/academic").setViewName("academic");
        registry.addViewController("/personnel").setViewName("personnel");

        registry.addViewController("/student/mytimetable").setViewName("student/mytimetable");
        registry.addViewController("/index").setViewName("common/home");
        registry.addViewController("/student/grade").setViewName("student/grade");
        registry.addViewController("/student/index").setViewName("student/mytimetable");
        registry.addViewController("/student/xuanke").setViewName("student/courseselection");
        registry.addViewController("/student/tuike").setViewName("student/coursedrop");
        registry.addViewController("/student/searchteacher").setViewName("student/searchteacher");

        registry.addViewController("/teacher/teacher-class").setViewName("teacher/teacher-class");
        registry.addViewController("/teacher/teacher-look").setViewName("teacher/teacher-look");
        registry.addViewController("/teacher/teacher-manage").setViewName("teacher/teacher-manage");
        registry.addViewController("/teacher/teacher-edit").setViewName("teacher/teacher-edit");

        registry.addViewController("/academic/course").setViewName("/academic/course");
        registry.addViewController("/academic/release-course").setViewName("/academic/release-course");
        registry.addViewController("/academic/edit-and-release").setViewName("/academic/edit-and-release");
        registry.addViewController("/academic/add-class").setViewName("/academic/add-class");
        registry.addViewController("/academic/add-student").setViewName("/academic/add-student");

        registry.addViewController("/personnel/add-teacher").setViewName("/personnel/add-teacher");
        registry.addViewController("/personnel/add-manage").setViewName("/personnel/add-manage");
        registry.addViewController("/personnel/edit-teacher").setViewName("/personnel/edit-teacher");
        registry.addViewController("/personnel/edit-manage").setViewName("/personnel/edit-manage");
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
