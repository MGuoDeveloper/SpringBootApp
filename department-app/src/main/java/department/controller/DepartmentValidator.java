package department.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import department.repository.*;

@Component
public class DepartmentValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return Department.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Department dep = (Department) obj;
		
		if(dep.getName().equals("")) {
			errors.rejectValue("name", "required", new Object[]{"'name'"}, "Please Input Department Name");
		}
		
		if(dep.getSalary_min_range() == null) {
			errors.rejectValue("salary_min_range", "", new Object[]{"'salary_min_range'"}, "Please Input Min Salary");
		}
		
		if(dep.getSalary_max_range() == null) {
			errors.rejectValue("salary_max_range", "", new Object[]{"'salary_max_range'"}, "Please Input Max Salary");
		}
		
		if(dep.getSalary_max_range() != null && dep.getSalary_min_range() != null 
				&& dep.getSalary_max_range().compareTo(dep.getSalary_min_range()) != 1) {
			errors.rejectValue("salary_max_range", "", new Object[]{"'salary_max_range'"}, 
					"Max Salary Should bigger than Min Salary");
		}
	}
	
}
