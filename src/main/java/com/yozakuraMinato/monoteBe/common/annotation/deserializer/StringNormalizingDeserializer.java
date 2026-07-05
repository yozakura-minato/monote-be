package com.yozakuraMinato.monoteBe.common.annotation.deserializer;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

public final class StringNormalizingDeserializer extends ValueDeserializer<String> {

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JacksonException {
        String plainString = jsonParser.getString();

        return plainString == null || plainString.isBlank()
                ? null
                : plainString.strip();
    }

}
