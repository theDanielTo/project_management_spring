package com.dto.pma.validation;

import com.dto.pma.dao.EmployeeRepository;
import com.dto.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

    @Autowired
    EmployeeRepository empRepo;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        System.out.println("Entered validation method..");

        Employee emp = empRepo.findByEmail(value);

        return emp == null;
    }
}
