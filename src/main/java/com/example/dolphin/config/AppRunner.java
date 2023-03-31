package com.example.dolphin.config;


import cn.hutool.core.io.FileUtil;
import com.example.dolphin.infrastructure.consts.StringPool;
import com.example.dolphin.infrastructure.utils.IocUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * 项目启动后执行的操作
 *
 * @author 王景阳
 * @date 2023-03-25 17:08
 */
@Slf4j
@Component
public class AppRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        try {
            ConfigurableEnvironment environment = IocUtil.get(ConfigurableEnvironment.class);
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            String port = environment.getProperty("local.server.port");
            log.info("http://{}:{}", hostAddress, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkAndCreate();
    }

    private void checkAndCreate() {
        FileUtil.mkdir(StringPool.VIDEO_RESOURCE_PATH);
        FileUtil.mkdir(StringPool.IMAGE_RESOURCE_PATH);
    }
}