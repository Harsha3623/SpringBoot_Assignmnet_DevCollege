package com.example.DevCollege.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonStrictConfig {

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();

        // Disallow coercion from String to Integer
        mapper.coercionConfigFor(Integer.class)
                .setCoercion(CoercionInputShape.String, CoercionAction.Fail);

        // Disallow coercion from String to Float
        mapper.coercionConfigFor(Float.class)
                .setCoercion(CoercionInputShape.String, CoercionAction.Fail);

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }
}
