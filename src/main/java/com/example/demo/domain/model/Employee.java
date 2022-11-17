package com.example.demo.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Employee {

    private final static String MEN = "男性";
    private final static String WOMEN = "女性";

    @NotNull
    @Id
    private Integer id;
    @NotNull
    private String name;
    @Min(20)
    private Integer age;
    private Integer gender;
    @Transient
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
