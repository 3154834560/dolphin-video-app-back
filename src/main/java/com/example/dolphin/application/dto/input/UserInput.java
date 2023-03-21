package com.example.dolphin.application.dto.input;

import com.example.dolphin.domain.entity.User;
import com.example.dolphin.domain.enums.SexEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 王景阳
 * @date 2022/10/29 20:51
 */
@Data
public class UserInput {

    private String userName;

    private String name;

    private String nick;

    private String password;

    private SexEnum sex;

    private boolean isAdmin;

    private LocalDateTime birthday;

    private String phone;

    private String introduction;

    public void copy(User user) {
        user.setName(name);
        user.setNick(nick);
        user.setUserName(userName);
        user.setPassword(password);
        user.setSex(sex);
        user.setBirthday(birthday);
        user.setPhone(phone);
        user.setIntroduction(introduction);
    }

    public User to() {
        User user = new User(userName, name, nick, password, sex);
        user.setBirthday(birthday);
        user.setPhone(phone);
        user.setIntroduction(introduction);
        return user;
    }
}
