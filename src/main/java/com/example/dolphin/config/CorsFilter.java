package com.example.dolphin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 王景阳
 * @date 2023-03-21 17:31
 */
@Slf4j
@Component
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        log.info("{} --> {}", req.getMethod(), req.getRequestURL());
        // add cors header
        res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
        res.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
        // skip OPTIONS request
        if (req.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return;
        }
        chain.doFilter(request, response);
    }
}
