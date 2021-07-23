package com.system.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class Student {
    private String sid;
    private String sname;
    private String email;
    private String className;
    private int mid;
    private int classId;

    private int grade;
    private String mname;
}
