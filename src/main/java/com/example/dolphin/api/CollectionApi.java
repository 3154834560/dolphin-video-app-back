package com.example.dolphin.api;

import com.example.dolphin.application.dto.output.CollectionOutput;
import com.example.dolphin.infrastructure.model.rest.R;
import com.example.dolphin.application.dto.output.VideoOutput;
import com.example.dolphin.application.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收藏
 *
 * @author 王景阳
 * @date 2022/11/10 18:48
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/dolphin/collection")
public class CollectionApi {

    private final CollectionService collectionService;

    /**
     * 获取指定用户的所有的收藏
     */
    @GetMapping
    public R<List<CollectionOutput>> getAllCollect(@RequestParam("userName") String userName) {
        return R.data(collectionService.getAllCollection(userName));
    }

    /**
     * 收藏视频
     */
    @PostMapping
    public R<Boolean> collect(@RequestParam("userName") String userName, @RequestParam("videoId") String videoId) {
        return R.data(collectionService.collection(userName, videoId));
    }

    /**
     * 取消收藏
     */
    @DeleteMapping
    public R<Boolean> unCollect(@RequestParam("userName") String userName, @RequestParam("videoId") String videoId) {
        return R.data(collectionService.unCollection(userName, videoId));
    }
}
