package com.system.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Data
@NoArgsConstructor
public class StudentBaseInfo {
    private String origin;
    private String politic;
    private Date birthday;
    private String mname;
    private int ssex;
    private int old;
    private String idCard;
}
