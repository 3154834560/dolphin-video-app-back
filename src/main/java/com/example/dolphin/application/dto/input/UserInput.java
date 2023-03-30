package com.example.dolphin.application.dto.input;

import com.example.dolphin.domain.enums.SexEnum;
import com.example.dolphin.domain.model.User;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 王景阳
 * @date 2022/10/29 20:51
 */
@Data
public class UserInput {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nick;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private SexEnum sex;
    /**
     * 生日
     */
    private LocalDateTime birthday;
    /**
     * 电话
     */
    private String phone;
    /**
     * 个人简介
     */
    private String introduction;

    public void copy(User user) {
        user.setNick(nick);
        user.setUserName(userName);
        user.setPassword(password);
        user.setSex(sex);
        user.setBirthday(birthday);
        user.setPhone(phone);
        user.setIntroduction(introduction);
    }

    public User to() {
        User user = new User(userName, nick, password, sex);
        user.setBirthday(birthday);
        user.setPhone(phone);
        user.setIntroduction(introduction);
        return user;
    }
}
