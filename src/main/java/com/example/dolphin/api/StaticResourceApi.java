package com.example.dolphin.api;

import com.example.dolphin.config.converter.MTAppDateTimeConverter;
import com.example.dolphin.infrastructure.consts.StringPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;
import java.util.Objects;

/**
 * @author 王景阳
 * @date 2022/11/10 18:48
 */
@Configuration
public class StaticResourceApi extends WebMvcConfigurationSupport {

    /**
     * 访问静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/videos/**")
                .addResourceLocations("file:" + StringPool.VIDEO_RESOURCE_PATH);
        registry.addResourceHandler("/static/images/**")
                .addResourceLocations("file:" + StringPool.IMAGE_RESOURCE_PATH);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new MTAppDateTimeConverter());
    }


    @Autowired(required = false)
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        if (Objects.isNull(mappingJackson2HttpMessageConverter)) {
            converters.add(0, new MappingJackson2HttpMessageConverter());
        } else {
            converters.add(0, mappingJackson2HttpMessageConverter);
        }
    }
}
