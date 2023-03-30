package com.example.dolphin.domain.model;

import com.example.dolphin.domain.enums.SexEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
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

    public User(String userName, String nick, String password, SexEnum sex) {
        this.userName = userName;
        this.nick = nick;
        this.password = password;
        this.sex = sex;
    }
    /**
     * 用户名
     */
    @Column(unique = true)
    @Setter
    private String userName;
    /**
     * 昵称
     */
    @Setter
    private String nick;
    /**
     * 密码
     */
    @Setter
    private String password;
    /**
     * 头像名称-带后缀
     */
    @Setter
    private String headPortraitName;
    /**
     * 性别
     */
    @Setter
    @Enumerated(value = EnumType.STRING)
    private SexEnum sex;
    /**
     * 生日
     */
    //    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    //    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Setter
    private LocalDateTime birthday;
    /**
     * 电话
     */
    @Setter
    private String phone;
    /**
     * 个人简介
     */
    @Setter
    private String introduction;
}
