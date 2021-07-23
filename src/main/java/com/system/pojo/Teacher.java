package com.system.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class Teacher {
    private String tid;
    private String tname;
    private String rank;
    private String politic;
    private String degree;
    private int tsex;
    private int tsalary;

    private String[] course;

    private String tSexs;
}
