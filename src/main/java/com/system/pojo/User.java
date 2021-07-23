package com.system.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class User {
    private String id;
    private String passWord;
    private String email;
    private int role;
}
