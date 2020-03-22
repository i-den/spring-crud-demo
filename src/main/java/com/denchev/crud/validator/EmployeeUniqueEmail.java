package com.denchev.crud.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = EmployeeUniqueEmailValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface EmployeeUniqueEmail {

    public String message() default "The email is already in use";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
