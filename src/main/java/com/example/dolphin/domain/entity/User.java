package com.example.dolphin.domain.entity;

import com.example.dolphin.acomm.domain.BaseEntity;
import com.example.dolphin.domain.enums.SexEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

/**
 * @author 王景阳
 * @date 2022/10/29 20:40
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    public User(String userName, String name, String nick, String password, SexEnum sex) {
        this.userName = userName;
        this.name = name;
        this.nick = nick;
        this.password = password;
        this.sex = sex;
    }

    @Setter
    private String userName;

    @Setter
    private String name;

    @Setter
    private String nick;

    @Setter
    private String password;

    @Setter
    private String headPortraitUrl;

    @Setter
    private String headPortraitName;

    @Setter
    @Enumerated(value = EnumType.STRING)
    private SexEnum sex;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Setter
    private LocalDateTime birthday;

    @Setter
    private String phone;

    @Setter
    private String introduction;

    private boolean isAdmin;
}
