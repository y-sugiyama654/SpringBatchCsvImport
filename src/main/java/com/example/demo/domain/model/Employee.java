package com.example.demo.domain.model;

import lombok.Data;

@Data
public class Employee {

    private final static String MEN = "男性";
    private final static String WOMEN = "女性";

    private Integer id;
    private String name;
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
