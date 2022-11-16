package com.example.dolphin.api;

import com.example.dolphin.acomm.model.rest.R;
import com.example.dolphin.application.service.CollectionService;
import com.example.dolphin.application.service.ConcernService;
import com.example.dolphin.domain.entity.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王景阳
 * @date 2022/11/10 18:48
 */
@RestController
@RequestMapping("/dolphin/collection")
public class CollectionApi {

    @Autowired
    private CollectionService collectionService;

    @GetMapping
    public R<?> getAllConcern(@RequestParam("userName") String userName) {
        return R.data(collectionService.getAllCollection(userName));
    }

    @PostMapping
    public R<?> concern(@RequestParam("userName") String userName, @RequestParam("videoId") String videoId) {
        return R.data(collectionService.collection(userName, videoId));
    }

    @DeleteMapping
    public R<?> unconcern(@RequestParam("userName") String userName, @RequestParam("videoId") String videoId) {
        return R.data(collectionService.unCollection(userName, videoId));
    }
}
