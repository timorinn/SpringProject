package com.example.springProject;

import java.lang.annotation.*;


@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectString {
	String s();
}
