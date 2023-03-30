package com.example.dolphin.application.dto.input;

import com.example.dolphin.infrastructure.consts.StringPool;
import lombok.Data;

/**
 * @author 王景阳
 * @date 2022/11/30 8:42
 */
@Data
public class VideoInput {
    /**
     *  用户名
     */
    private String userName;
    /**
     *  视频简介
     */
    private String videoIntroduction;
    /**
     *  视频名-带后缀
     */
    private String videoName;
    /**
     *  视频封面名-带后缀
     */
    private String coverName;
    /**
     *  是否有封面
     */
    private boolean hasCover;
    /**
     *  是否是最后一块
     */
    private boolean end;

    public String getCoverPath(String suffix) {
        return StringPool.IMAGE_RESOURCE_PATH + getCoverName(suffix);
    }

    public String getCoverName(String suffix) {
        if (coverName == null) {
            return videoName.substring(0, videoName.lastIndexOf(StringPool.DOT) + 1) + suffix;
        }
        return coverName;
    }

    public String getVideoPath() {
        return StringPool.VIDEO_RESOURCE_PATH + videoName;
    }
}
