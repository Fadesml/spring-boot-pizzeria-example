package ru.fadesml.pizzeria.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Log
@Converter
public class ListConverter implements AttributeConverter<List<?>, String> {
    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List attribute) {
        try {
            return OBJECT_MAPPER.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {

            log.warning("JSON writing error: " + e);
        }

        return null;
    }

    @Override
    public List<?> convertToEntityAttribute(String dbData) {
        try {
            return OBJECT_MAPPER.readValue(dbData, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.warning("JSON reading error: " + e);
        }

        return null;
    }
}

