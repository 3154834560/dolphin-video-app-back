package com.example.dolphin.api;

import com.example.dolphin.acomm.model.rest.R;
import com.example.dolphin.application.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王景阳
 * @date 2022/11/10 18:48
 */
@RestController
@RequestMapping("/dolphin/comment")
public class CommentApi {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public R<?> getAllConcern(@RequestParam("videoId") String videoId) {
        return R.data(commentService.getAllComment(videoId));
    }

    @PostMapping
    public R<?> concern(@RequestParam("userName") String userName, @RequestParam("videoId") String videoId, @RequestParam("content") String content) {
        return R.data(commentService.comment(userName, videoId, content));
    }

    @DeleteMapping
    public R<?> unconcern(@RequestParam("id") String id) {
        return R.data(commentService.unComment(id));
    }
}
