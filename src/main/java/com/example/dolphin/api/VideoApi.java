package com.example.dolphin.api;

import com.example.dolphin.acomm.model.rest.R;
import com.example.dolphin.application.dto.output.VideoOutput;
import com.example.dolphin.application.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.util.List;

/**
 * 视频
 *
 * @author 王景阳
 * @date 2022/10/27 20:44
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dolphin/video")
public class VideoApi {


    private final VideoService service;

    /**
     * 获取指定用户的视频
     */
    @GetMapping("/all")
    public R<List<VideoOutput>> getAll(@RequestParam("userName") String userName) {
        return R.data(service.getAllBy(userName));
    }

    /**
     * 获取指定视频
     */
    @GetMapping
    public R<VideoOutput> getBy(@RequestParam("id") String id) {
        return R.data(service.getById(id));
    }

    /**
     * 随机获取视频
     */
    @GetMapping("/random/{index}")
    public R<List<VideoOutput>> randomGet(@PathVariable("index") int index) {
        return R.data(service.randomGet(index));
    }

    /**
     * 上传视频
     */
    @PostMapping
    public R<Boolean> uploadVideo(@RequestParam("userName") String userName, @RequestParam("introduction") String introduction, @RequestPart("video") Part video, @RequestPart(name = "cover", required = false) Part cover) {
        return R.data(service.uploadVideo(userName, introduction, video, cover));
    }

    /**
     * 分块上传视频
     */
    @PostMapping("/shard")
    public R<Boolean> uploadShardVideo(@RequestParam("videoInput") String videoInputStr, @RequestPart(name = "video") Part video, @RequestPart(name = "cover", required = false) Part cover) {
        return R.data(service.uploadVideo(videoInputStr, video, cover));
    }

    /**
     * 删除视频
     */
    @DeleteMapping("/{videoId}")
    public R<Boolean> delete(@PathVariable("videoId") String videoId) {
        return R.data(service.deleteBy(videoId));
    }

    /**
     * 更新视频
     */
    @PutMapping("/update/{videoId}")
    public R<VideoOutput> updateVideo(@PathVariable("videoId") String videoId, @RequestParam("introduction") String introduction) {
        return R.data(service.update(videoId, introduction));
    }
}
