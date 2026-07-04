package com.yozakuraMinato.monoteBe.shared.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.yozakuraMinato.monoteBe.shared.annotation.deserializer.StringNormalizingDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonDeserialize(using = StringNormalizingDeserializer.class)
public @interface NormalizedString {}
