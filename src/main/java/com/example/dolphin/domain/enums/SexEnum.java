package com.example.dolphin.domain.enums;

/**
 * @author 王景阳
 * @date 2022/10/29 20:43
 */
public enum SexEnum {

    /**
     * 性别
     */
    MALE("男"),FEMALE("女");

    private final String sex;

    SexEnum(String sex) {
        this.sex=sex;
    }

    public String getSex() {
        return sex;
    }
}
