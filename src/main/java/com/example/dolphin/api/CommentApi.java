package com.example.dolphin.api;

import com.example.dolphin.infrastructure.model.rest.R;
import com.example.dolphin.application.dto.input.CommentInput;
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
     * 获取指定视频评论数量
     */
    @GetMapping("/count")
    public R<Integer> getCommentCount(@RequestParam("videoId") String videoId) {
        return R.data(commentService.getCommentCountBy(videoId));
    }

    /**
     * 评论
     */
    @PostMapping
    public R<Boolean> comment(@RequestBody CommentInput input) {
        return R.data(commentService.comment(input));
    }

    /**
     * 删除评论
     */
    @DeleteMapping
    public R<Boolean> unComment(@RequestParam("id") String id) {
        return R.data(commentService.unComment(id));
    }
}
