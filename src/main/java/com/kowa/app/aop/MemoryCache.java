package com.kowa.app.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD })
@Retention(RetentionPolicy.CLASS)
public @interface MemoryCache {
}
