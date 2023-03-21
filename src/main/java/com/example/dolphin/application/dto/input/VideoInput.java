package com.example.dolphin.application.dto.input;

import com.example.dolphin.infrastructure.consts.StringPool;
import lombok.Data;

/**
 * @author 王景阳
 * @date 2022/11/30 8:42
 */
@Data
public class VideoInput {

    private String userName;

    private String videoIntroduction;

    private String videoName;

    private String coverName;

    private boolean hasCover;

    private boolean end;

    public String getCoverName(String suffix) {
        if (coverName == null) {
            return videoName.substring(0, videoName.lastIndexOf(StringPool.DOT) + 1) + suffix;
        }
        return coverName;
    }

}
