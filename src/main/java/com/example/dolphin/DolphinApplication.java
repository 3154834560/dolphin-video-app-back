package com.example.dolphin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author 王景阳
 * @date 2022/10/27 15:16
 */
@Slf4j
@SpringBootApplication
public class DolphinApplication {

    public static void main(String[] args) {
        ConfigurableEnvironment environment = SpringApplication.run(DolphinApplication.class, args).getEnvironment();
        printHttp(environment);
    }

    private static void printHttp(ConfigurableEnvironment environment) {
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            String port = environment.getProperty("local.server.port");
            log.info("http://{}:{}", hostAddress, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
