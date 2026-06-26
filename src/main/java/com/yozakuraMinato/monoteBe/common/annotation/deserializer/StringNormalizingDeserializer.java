package com.yozakuraMinato.monoteBe.common.annotation.deserializer;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

public final class StringNormalizingDeserializer extends ValueDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        String plainValue = p.getString();
        if (plainValue == null) return null;
        String strippedValue = plainValue.strip();
        return strippedValue.isEmpty() ? null : strippedValue;
    }

}
