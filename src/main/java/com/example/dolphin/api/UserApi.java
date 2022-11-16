package com.example.dolphin.api;

import com.example.dolphin.acomm.model.rest.R;
import com.example.dolphin.application.dto.input.UserInput;
import com.example.dolphin.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author 王景阳
 * @date 2022/10/29 20:57
 */
@RestController
@RequestMapping("/dolphin/user")
public class UserApi {

    @Autowired
    UserService service;

    @GetMapping("/all")
    public R<?> getAll() {
        return R.data(service.getAll());
    }

    @PostMapping
    public R<?> create(@RequestBody UserInput input) {
        return R.data(service.createBy(input));
    }

    @PutMapping
    public R<?> update(@RequestBody UserInput input) {
        return R.data(service.updateBy(input));
    }

    @GetMapping
    public R<?> get(@RequestParam("userName") String userName) {
        return R.data(service.getBy(userName));
    }

    @GetMapping("/verify")
    public R<?> verify(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        return R.data(service.verify(userName, password));
    }

    @DeleteMapping
    public R<?> delete(@RequestParam("userName") String userName) {
        return R.data(service.deleteBy(userName));
    }

}
