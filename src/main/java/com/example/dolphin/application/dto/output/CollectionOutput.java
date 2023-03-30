package com.example.dolphin.application.dto.output;

import com.example.dolphin.domain.model.Video;
import lombok.Data;

/**
 * @author 王景阳
 * @date 2022/11/10 21:08
 */
@Data
public class CollectionOutput {
    /**
     * 视频id
     */
    private String videoId;
    /**
     * 视频封面-带后缀
     */
    private String coverName;
    /**
     * 点赞数
     */
    private long numbers;

    public static CollectionOutput of(Video video) {
        CollectionOutput output = new CollectionOutput();
        output.setVideoId(video.getId());
        output.setCoverName(video.getCoverName());
        output.setNumbers(video.getNumbers());
        return output;
    }

}
