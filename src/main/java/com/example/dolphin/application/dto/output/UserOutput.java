package com.example.dolphin.application.dto.output;

import com.example.dolphin.domain.entity.User;
import com.example.dolphin.domain.enums.SexEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author 王景阳
 * @date 2022/11/11 20:39
 */
@Data
@Accessors(chain = true)
public class UserOutput {

    private String userName;

    private String name;

    private String nick;

    private String password;

    private String headPortraitUrl;

    private SexEnum sex;

  //  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private LocalDateTime birthday;

    private String phone;

    private String introduction;

    private boolean isAdmin;

    public static UserOutput of(User user){
        return new UserOutput()
                .setUserName(user.getUserName())
                .setNick(user.getNick())
                .setName(user.getName())
                .setPassword(user.getPassword())
                .setHeadPortraitUrl(user.getHeadPortraitUrl())
                .setSex(user.getSex())
                .setBirthday(user.getBirthday())
                .setPhone(user.getPhone())
                .setIntroduction(user.getIntroduction())
                .setAdmin(user.isAdmin());
    }

}
