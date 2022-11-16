package com.example.dolphin.api;

import com.example.dolphin.acomm.model.rest.R;
import com.example.dolphin.application.service.ConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王景阳
 * @date 2022/11/10 18:48
 */
@RestController
@RequestMapping("/dolphin/concern")
public class ConcernApi {

    @Autowired
    ConcernService concernService;

    @GetMapping
    public R<?> getAllConcern(@RequestParam("userName") String userName) {
        return R.data(concernService.getAllConcern(userName));
    }

    @PostMapping
    public R<?> concern(@RequestParam("userName") String userName, @RequestParam("concernedUserName") String concernedUserName) {
        return R.data(concernService.concern(userName, concernedUserName));
    }

    @DeleteMapping
    public R<?> unconcern(@RequestParam("userName") String userName, @RequestParam("concernedUserName") String concernedUserName) {
        return R.data(concernService.unconcern(userName, concernedUserName));
    }

}
