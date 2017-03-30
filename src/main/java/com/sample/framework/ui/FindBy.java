package com.sample.framework.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.sample.framework.Platform;

@Target(ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Repeatable(FindByList.class)
public @interface FindBy {
	String locator();
	Platform platform() default Platform.ANY;
}
