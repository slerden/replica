package ru.itword.replica.service.validation.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.Validator;
import ru.itword.replica.exceptions.ValidationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Created by Itword on 27.07.2017.
 */
@Component
@Aspect
public class ValidationAspect {

    @Autowired
    ApplicationContext applicationContext;

    @Around(value = "validatedArgPointcut()")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        Object[] args = proceedingJoinPoint.getArgs();
        Map<Object, ValidatedArg> validatedArgMap = new HashMap<>();
        for (int i = 0; i<parameters.length; i++) {
            Parameter parameter = parameters[i];
            if(parameter.isAnnotationPresent(ValidatedArg.class)){
                if (args[i] != null) {
                    validatedArgMap.put(args[i], parameter.getAnnotation(ValidatedArg.class));
                }
            }
        }
        for (Object o : validatedArgMap.keySet()) {
            ValidatedArg validatedArg = validatedArgMap.get(o);
            Class<? extends Validator>[] value = validatedArg.value();
            DirectFieldBindingResult errors = new DirectFieldBindingResult(o, "obj");
            for (Class<? extends Validator> aClass : value) {
                Validator validator = applicationContext.getBean(aClass);
                if(validator.supports(o.getClass())){
                    validator.validate(o, errors);
                }
            }
            if(errors.hasErrors()) throw new ValidationException(errors);
        }
        return proceedingJoinPoint.proceed();
    }

    @Pointcut(value = "execution(public * *(.., @ValidatedArg (*), ..))")
    private void validatedArgPointcut(){}
}
