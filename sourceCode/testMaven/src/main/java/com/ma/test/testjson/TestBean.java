package com.ma.test.testjson;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ma
 */
@Data
public class TestBean {
    @Setter @Getter
    private String name;
    @Setter @Getter
    private Integer age;
    @Setter @Getter
    private String sex;
    @Setter @Getter
    private String adress;

}
