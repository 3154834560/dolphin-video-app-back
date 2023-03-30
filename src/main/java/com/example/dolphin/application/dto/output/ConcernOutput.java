package com.example.dolphin.application.dto.output;

import com.example.dolphin.domain.model.Concern;
import lombok.Data;

/**
 * @author 王景阳
 * @date 2022/11/10 21:08
 */
@Data
public class ConcernOutput {
    /**
     * 用户名
     */
    private String userName;

    public static ConcernOutput of(Concern concern) {
        ConcernOutput output = new ConcernOutput();
        output.setUserName(concern.getConcernedUser().getUserName());
        return output;
    }

}
