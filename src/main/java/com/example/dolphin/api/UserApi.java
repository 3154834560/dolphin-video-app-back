package com.example.dolphin.api;

import com.example.dolphin.acomm.model.rest.R;
import com.example.dolphin.application.dto.input.UserInput;
import com.example.dolphin.application.dto.output.UserOutput;
import com.example.dolphin.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户
 *
 * @author 王景阳
 * @date 2022/10/29 20:57
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/dolphin/user")
public class UserApi {

    private final UserService service;

    /**
     * 获取所有用户
     */
    @GetMapping("/all")
    public R<List<UserOutput>> getAll() {
        return R.data(service.getAll());
    }

    /**
     * 创建用户
     */
    @PostMapping
    public R<UserOutput> create(@RequestBody UserInput input) {
        return R.data(service.createBy(input));
    }

    /**
     * 更新用户信息
     */
    @PutMapping
    public R<UserOutput> update(@RequestBody UserInput input) {
        return R.data(service.updateBy(input));
    }

    /**
     * 获取指定用户
     */
    @GetMapping
    public R<UserOutput> get(@RequestParam("userName") String userName) {
        return R.data(service.getBy(userName));
    }

    /**
     * 验证密码
     */
    @GetMapping("/verify")
    public R<Integer> verify(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        return R.data(service.verify(userName, password));
    }

    /**
     * 删除指定用户
     */
    @DeleteMapping
    public R<Boolean> delete(@RequestParam("userName") String userName) {
        return R.data(service.deleteBy(userName));
    }

}
