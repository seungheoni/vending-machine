package com.example.openapi;

import io.swagger.v3.oas.models.media.StringSchema;
import org.bson.types.ObjectId;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    static {
        /*
         * spring-open-api 에서 변수 타입이 ObjectId인 경우,
         * String 타입으로 매핑
         */
        SpringDocUtils.getConfig().replaceWithSchema(ObjectId.class, new StringSchema());
    }
}
