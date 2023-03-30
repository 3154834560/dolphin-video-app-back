package com.example.dolphin.api;

import com.example.dolphin.infrastructure.model.rest.R;
import com.example.dolphin.application.service.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 点赞
 *
 * @author 王景阳
 * @date 2022/11/23 17:16
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/dolphin/support")
public class SupportApi {

    private final SupportService service;

    /**
     * 点赞
     */
    @PostMapping
    public R<Boolean> supportVideo(@RequestParam("userName") String userName, @RequestParam("videoId") String videoId, @RequestParam("n") int n) {
        return R.data(service.support(userName, videoId, n));
    }

    /**
     * 是否点赞
     */
    @GetMapping
    public R<Boolean> isSupport(@RequestParam("userName") String userName, @RequestParam("videoId") String videoId) {
        return R.data(service.isSupport(userName, videoId));
    }
}
