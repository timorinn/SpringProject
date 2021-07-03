package com.example.SpringProject_1;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;


@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectString {
	String s();
}
