package com.yozakuraMinato.monoteBe.shared.annotation.deserializer;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

public final class EmailNormalizingDeserializer extends ValueDeserializer<String> {

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JacksonException {
        String plainEmail = jsonParser.getString();
        return plainEmail == null || plainEmail.isBlank()
                ? null
                : plainEmail.strip().toLowerCase();
    }

}
