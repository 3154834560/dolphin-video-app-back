package com.example.dolphin.api;

import com.example.dolphin.acomm.model.rest.R;
import com.example.dolphin.application.dto.output.CommentOutput;
import com.example.dolphin.application.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论
 *
 * @author 王景阳
 * @date 2022/11/10 18:48
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/dolphin/comment")
public class CommentApi {

    private final CommentService commentService;

    /**
     * 获取指定视频的所以评论
     */
    @GetMapping
    public R<List<CommentOutput>> getAllComment(@RequestParam("videoId") String videoId) {
        return R.data(commentService.getAllComment(videoId));
    }

    /**
     * 评论
     */
    @PostMapping
    public R<Boolean> comment(@RequestParam("userName") String userName, @RequestParam("videoId") String videoId, @RequestParam("content") String content) {
        return R.data(commentService.comment(userName, videoId, content));
    }

    /**
     * 删除评论
     */
    @DeleteMapping
    public R<Boolean> unComment(@RequestParam("id") String id) {
        return R.data(commentService.unComment(id));
    }
}
