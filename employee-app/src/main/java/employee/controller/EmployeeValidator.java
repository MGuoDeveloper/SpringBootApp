package employee.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.client.RestTemplate;

import employee.respository.Department;
import employee.respository.Employee;

@Component
public class EmployeeValidator implements Validator {

	private static final String DEP_REST_URI = "http://localhost:8081";
	private static RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return Employee.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Employee emp = (Employee) obj;
		
		if(emp.getName().equals("")) {
			errors.rejectValue("name", "required", new Object[]{"'name'"}, "Please Input Employee Name");
		}
		
		if(emp.getSalary() == null) {
			errors.rejectValue("salary", "", new Object[]{"'salary'"}, "Please Input Salary");
		} else {
			Department dep = restTemplate.getForObject(DEP_REST_URI + "/department/" + emp.getDept(), Department.class);
			if(emp.getSalary().compareTo(dep.getSalary_max_range()) > 0 || emp.getSalary().compareTo(dep.getSalary_min_range()) < 0) {
				errors.rejectValue("salary", "outOfRange", new Object[]{"'salary'"}, 
						"Salary should be in range " + dep.getSalary_min_range() + " - " + dep.getSalary_max_range());
			}
		}
		
	}
	
}
