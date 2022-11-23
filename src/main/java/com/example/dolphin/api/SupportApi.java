package com.example.dolphin.api;

import com.example.dolphin.acomm.model.rest.R;
import com.example.dolphin.application.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王景阳
 * @date 2022/11/23 17:16
 */
@Controller
@RequestMapping("/dolphin/support")
public class SupportApi {

    @Autowired
    private VideoService service;

    @PostMapping
    @ResponseBody
    public R<?> supportVideo(@RequestParam("userName") String userName, @RequestParam("videoId") String videoId, @RequestParam("n") int n) {
        return R.data(service.support(userName,videoId,n));
    }

    @GetMapping
    @ResponseBody
    public R<?> isSupport(@RequestParam("userName") String userName, @RequestParam("videoId") String videoId) {
        return R.data(service.isSupport(userName,videoId));
    }
}
