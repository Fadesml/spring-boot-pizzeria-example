package ru.fadesml.pizzeria.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Map;

@Log
@Converter
public class HashMapConverter implements AttributeConverter<Map<?, ?>, String> {
    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<?, ?> attribute) {
        try {
            return OBJECT_MAPPER.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {

            log.warning("JSON writing error: " + e);
        }

        return null;
    }

    @Override
    public Map<?, ?> convertToEntityAttribute(String dbData) {
        try {
            return OBJECT_MAPPER.readValue(dbData, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.warning("JSON reading error: " + e);
        }

        return null;
    }
}
