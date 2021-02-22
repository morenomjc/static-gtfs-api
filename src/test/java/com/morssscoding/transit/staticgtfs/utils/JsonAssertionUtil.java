package com.morssscoding.transit.staticgtfs.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class JsonAssertionUtil {

    public ResultMatcher assertHasJsonFields(String path, Class<?> clazz){
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructType(clazz);
        BeanDescription beanDescription = mapper.getSerializationConfig().introspect(javaType);
        List<BeanPropertyDefinition> properties = beanDescription.findProperties();

        List<ResultMatcher> jsonFieldsMatchers = properties.stream().map(beanPropertyDefinition -> {
            String jsonField = beanPropertyDefinition.getField().getAnnotated().getAnnotation(JsonProperty.class).value();
            return jsonPath(path, hasKey(jsonField));
        }).collect(Collectors.toList());

        ResultMatcher[] resultMatchersArr = new ResultMatcher[jsonFieldsMatchers.size()];
        jsonFieldsMatchers.toArray(resultMatchersArr);
        return matchAll(resultMatchersArr);
    }
}
