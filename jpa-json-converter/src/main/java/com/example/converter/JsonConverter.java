package com.example.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.GenericTypeResolver;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JsonConverter<T> implements AttributeConverter<T, String> {

    protected final ObjectMapper objectMapper;

    public JsonConverter() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        if (ObjectUtils.isEmpty(attribute)) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if (StringUtils.hasText(dbData)) {
            Class<?> aClass = GenericTypeResolver.resolveTypeArgument(getClass(), JsonConverter.class);
            try {
                return (T) objectMapper.readValue(dbData, aClass);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}