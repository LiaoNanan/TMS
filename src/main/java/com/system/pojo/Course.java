package com.system.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class Course {
    private String cname;
    private String cno;
    private String tname;
    private String tid;
    private int grade;
    private String mname;
    private String className;
    private int period;
    private int credits;
    private int mid;
    private int classId;
    private String isElective;
    private double gradePoint;
}
