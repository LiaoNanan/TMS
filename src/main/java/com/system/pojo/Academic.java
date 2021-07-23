package com.system.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class Academic {
    private String acid;
    private String acname;
    private int acsex;
    private int acsalary;

    private String acSexs;
}
