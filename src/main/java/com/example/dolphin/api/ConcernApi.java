package com.example.dolphin.api;

import com.example.dolphin.acomm.model.rest.R;
import com.example.dolphin.application.dto.output.ConcernOutput;
import com.example.dolphin.application.service.ConcernService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 关注
 *
 * @author 王景阳
 * @date 2022/11/10 18:48
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/dolphin/concern")
public class ConcernApi {

    private final ConcernService concernService;

    /**
     * 获取指定用户的所有关注
     */
    @GetMapping
    public R<List<ConcernOutput>> getAllConcern(@RequestParam("userName") String userName) {
        return R.data(concernService.getAllConcern(userName));
    }

    /**
     * 关注
     */
    @PostMapping
    public R<Boolean> concern(@RequestParam("userName") String userName, @RequestParam("concernedUserName") String concernedUserName) {
        return R.data(concernService.concern(userName, concernedUserName));
    }

    /**
     * 取消关注
     */
    @DeleteMapping
    public R<Boolean> unconcern(@RequestParam("userName") String userName, @RequestParam("concernedUserName") String concernedUserName) {
        return R.data(concernService.unconcern(userName, concernedUserName));
    }

}
