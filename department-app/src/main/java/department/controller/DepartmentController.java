package department.controller;

import java.util.*;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import department.repository.*;

@Controller
@SuppressWarnings({"unchecked", "rawtypes"})
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private DepartmentValidator departmentValidator;
    
	@RequestMapping(value = "/dep", method = RequestMethod.GET)
    public String welcome(Map model) {
		model.put("departments", departmentRepository.findAll());
		return "index";
    }
	
	@RequestMapping(value = "/newdepartment", method = RequestMethod.GET)
    public String update(Map model) {
		model.put("department", new Department());
		return "create";
    }
	
	@RequestMapping(value = "/savedepartment", method = RequestMethod.POST)
    public String save(@Valid Department department, BindingResult result, Map model) {
		departmentValidator.validate(department, result);
		if (result.hasErrors()) {
			return "create";
		}
		
		department = (Department) model.get("department");
		departmentRepository.save(department);
		return "redirect:/dep";
    }
}
