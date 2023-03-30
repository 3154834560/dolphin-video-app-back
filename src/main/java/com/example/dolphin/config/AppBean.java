package com.example.dolphin.config;

import com.example.dolphin.infrastructure.utils.IocUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author 王景阳
 * @date 2023-03-21 17:32
 */
@Configuration(proxyBeanMethods = false)
@Slf4j
public class AppBean {
    @Bean
    @Lazy(value = false)
    @ConditionalOnMissingBean
    IocUtil iocUtil() {
        return IocUtil.getInstance();
    }

    @Bean
    Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder
                .serializerByType(LocalDateTime.class, localDateTimeJsonSerializer())
                .deserializerByType(LocalDateTime.class, localDateTimeJsonDeserializer());
    }

    /**
     * NOTICE: 注意使用 秒级 时间戳
     */
    public JsonSerializer<LocalDateTime> localDateTimeJsonSerializer() {
        return new JsonSerializer<LocalDateTime>() {
            @Override
            public void serialize(LocalDateTime dateTime, JsonGenerator generator, SerializerProvider provider) throws IOException {
                if (dateTime != null) {
                    long epochSecond = dateTime.toEpochSecond(ZoneOffset.ofHours(8));
                    generator.writeNumber(epochSecond);
                }
            }
        };
    }

    /**
     * NOTICE: 注意使用 秒级 时间戳
     */
    public JsonDeserializer<LocalDateTime> localDateTimeJsonDeserializer() {
        return new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
                long epochSecond = parser.getValueAsLong();
                return LocalDateTime.ofEpochSecond(epochSecond, 0, ZoneOffset.ofHours(8));
            }
        };
    }
}
