package ru.itword.replica.service.validation.resolvers;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

/**
 * Created by Itword on 23.07.2017.
 */
public interface BindingResultResolver<T> {
    T resolveBindingResult(Errors errors);
    T resolveBindingResult(Errors errors, T t);
}
