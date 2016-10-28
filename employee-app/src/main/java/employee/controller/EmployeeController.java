package employee.controller;

import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import employee.respository.*;

@Controller
@SuppressWarnings({"unchecked", "rawtypes"})
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private EmployeeValidator employeeValidator;
	private ArrayList<String> managerlist;
	private ArrayList<Department> deplist;
	
	public void getNames() {
		managerlist = new ArrayList<>();
		ArrayList<Employee> emplist = employeeRepository.findAll();
		emplist.forEach((emp) -> managerlist.add(emp.getName()));
	}
    
	@RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String welcome(Map model) {
		deplist = departmentRepository.findAll();
		Map depmap = new HashMap<>();
		deplist.forEach((dep) -> depmap.put(dep.getId(), dep.getName()));
		model.put("employees", employeeRepository.findAll());
		model.put("departments", depmap);
		return "index";
    }
	
	@RequestMapping(value = "/newemployee", method = RequestMethod.GET)
    public String create(Map model) {
		getNames();
		model.put("managerlist", managerlist);
		deplist = departmentRepository.findAll();
		model.put("deplist", deplist);
		model.put("message", "New");
		model.put("employee", new Employee());
		return "create";
    }
	
	@RequestMapping(value = "/editemployee{id}", method = RequestMethod.GET)
    public String update(Map model, @PathVariable("id") int id) {
		getNames();
		model.put("managerlist", managerlist);
		deplist = departmentRepository.findAll();
		model.put("deplist", deplist);
		model.put("message", "Edit");
		model.put("employee", employeeRepository.findById(id));
		return "create";
    }
	
	@RequestMapping(value = "/delemployee{id}", method = RequestMethod.GET)
    public String delete(Map model, @PathVariable("id") int id) {
		employeeRepository.delete(id);
		return "redirect:/emp";
    }
	
	@RequestMapping(value = "/saveemployee", method = RequestMethod.POST)
    public String save(@Valid Employee employee, BindingResult result, Map model) {
		employeeValidator.validate(employee, result);
		if (result.hasErrors()) {
			getNames();
			model.put("managerlist", managerlist);
			deplist = departmentRepository.findAll();
			model.put("deplist", deplist);
			model.put("message", "Edit");
			return "create";
		}
		
		employee = (Employee) model.get("employee");
		System.out.println(employee.getId());
		if(employee.getId() > 0) {
			employeeRepository.updateSalary(employee.getId(), employee.getSalary());
		} else {
			employeeRepository.save(employee);
		}
		return "redirect:/emp";
    }
	
    @RequestMapping("/employee/{id}")
    public @ResponseBody Employee getEmployeeById(@PathVariable("id") int id) {
    	return employeeRepository.findById(id);
    }
    
    @RequestMapping("/employees")
    public @ResponseBody ArrayList<Employee> getEmployeeList() {
    	return employeeRepository.findAll();
    }
}
