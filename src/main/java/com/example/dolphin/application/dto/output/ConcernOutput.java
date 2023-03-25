package com.example.dolphin.application.dto.output;

import com.example.dolphin.domain.entity.Concern;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 王景阳
 * @date 2022/11/10 21:08
 */
@Data
@Accessors(chain = true)
public class ConcernOutput {

    private String userName;

    private String headPortraitUrl;

    private String nick;

    public static ConcernOutput of(Concern concern) {
        return new ConcernOutput()
                .setUserName(concern.getConcernedUser().getUserName())
                .setHeadPortraitUrl(concern.getConcernedUser().getHeadPortraitUrl())
                .setNick(concern.getConcernedUser().getNick());
    }

}
