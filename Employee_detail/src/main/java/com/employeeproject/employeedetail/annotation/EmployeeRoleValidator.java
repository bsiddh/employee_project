package com.employeeproject.employeedetail.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation,String> {
    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintvalidatorcontext) {
     if(inputRole == null) return false;
     List<String> roles = List.of("USER","ADMIN");
     return roles.contains(inputRole);
    }
}