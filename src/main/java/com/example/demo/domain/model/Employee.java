package com.example.demo.domain.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Employee {

    private final static String MEN = "男性";
    private final static String WOMEN = "女性";

    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @Min(20)
    private Integer age;
    private Integer gender;
    private String genderString;

    /**
     * 性別の文字列を数値に変換
     */
    public void convertGenderStringToInt() {
        if (MEN.equals(genderString)) {
            gender = 1;
        } else if (WOMEN.equals(genderString)) {
            gender = 2;
        } else {
            String errorMsg = "Gender string is invalid : " + genderString;
            throw new IllegalStateException(errorMsg);
        }
    }

}
