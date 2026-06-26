package com.yozakuraMinato.monoteBe.common.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.yozakuraMinato.monoteBe.common.annotation.deserializer.EmailNormalizingDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonDeserialize(using = EmailNormalizingDeserializer.class)
public @interface NormalizedEmail {}
