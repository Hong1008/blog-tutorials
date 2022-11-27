package com.example.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
public class JsonArrayConverter<T> implements AttributeConverter<List<T>, String> {

    private final TypeReference<List<T>> typeReference;

    public JsonArrayConverter(TypeReference<List<T>> typeReference) {
        this.typeReference = typeReference;
    }

    @Override
    public String convertToDatabaseColumn(List<T> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        return objectMapper.writeValueAsString(attribute);
    }

    @Override
    public List<T> convertToEntityAttribute(String dbData) {
        if (StringUtils.hasText(dbData)) {
            return objectMapper.readValue(dbData, typeReference);
        }
        return null;
    }
}
