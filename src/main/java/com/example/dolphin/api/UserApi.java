package com.example.dolphin.api;

import com.example.dolphin.application.dto.input.UserInput;
import com.example.dolphin.application.dto.output.UserOutput;
import com.example.dolphin.application.service.UserService;
import com.example.dolphin.infrastructure.model.rest.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
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
     * 更新用户头像
     */
    @PutMapping("/head/portrait")
    public R<String> updateHeadPortrait(@RequestParam("userName") String userName, @RequestPart(name = "image") Part image) {
        return R.data(service.updateHeadPortraitBy(userName, image));
    }

    /**
     * 获取指定用户
     */
    @GetMapping
    public R<UserOutput> get(@RequestParam("userName") String userName) {
        return R.data(service.getBy(userName));
    }

    /**
     * 验证密码，返回0表示当前用户不存在，返回1表示密码错误，返回2表示密码正确
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
