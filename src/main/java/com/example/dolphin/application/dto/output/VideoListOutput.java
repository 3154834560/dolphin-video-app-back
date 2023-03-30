package com.example.dolphin.application.dto.output;


import com.example.dolphin.domain.model.Video;
import lombok.Data;

/**
 * @author 王景阳
 * @date 2022/10/29 20:53
 */
@Data
public class VideoListOutput {
    /**
     * 视频id
     */
    private String id;
    /**
     * 视频封面名称-带后缀
     */
    private String coverName;
    /**
     * 点赞数
     */
    private long numbers;

    public static VideoListOutput of(Video video) {
        if (video == null) {
            return null;
        }
        VideoListOutput output = new VideoListOutput();
        output.setId(video.getId());
        output.setNumbers(video.getNumbers());
        output.setCoverName(video.getCoverName());
        return output;
    }
}
