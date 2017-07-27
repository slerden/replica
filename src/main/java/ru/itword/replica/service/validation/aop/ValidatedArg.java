package ru.itword.replica.service.validation.aop;

import org.springframework.validation.Validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Itword on 27.07.2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface ValidatedArg {
    public Class<? extends Validator>[] value();
}
