package com.example.dolphin.application.dto.output;

import com.example.dolphin.domain.enums.SexEnum;
import com.example.dolphin.domain.model.User;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 王景阳
 * @date 2022/11/11 20:39
 */
@Data
public class UserOutput {
    /**
     *  用户名
     */
    private String userName;
    /**
     *  昵称
     */
    private String nick;
    /**
     *  密码
     */
    private String password;
    /**
     *  头像名称-带后缀
     */
    private String headPortraitName;
    /**
     *  性别
     */
    private SexEnum sex;
    /**
     *  生日
     */
    private LocalDateTime birthday;
    /**
     *  电话
     */
    private String phone;
    /**
     *  个人简介
     */
    private String introduction;

    public static UserOutput of(User user) {
        UserOutput output = new UserOutput();
        output.setUserName(user.getUserName());
        output.setNick(user.getNick());
        output.setPassword(user.getPassword());
        output.setSex(user.getSex());
        output.setBirthday(user.getBirthday());
        output.setPhone(user.getPhone());
        output.setIntroduction(user.getIntroduction());
        output.setHeadPortraitName(user.getHeadPortraitName());
        return output;
    }

}
