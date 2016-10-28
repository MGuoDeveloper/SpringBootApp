package department.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import department.repository.Department;
import department.repository.DepartmentRepository;

@RestController
public class DepartmentRest {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@RequestMapping(value = "/department/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDepartmentById(@PathVariable("id") int id) {
    	Department department = departmentRepository.findById(id);
    	if(department == null) return new ResponseEntity<String>("Not Found Department", HttpStatus.NOT_FOUND);
    	return new ResponseEntity<Department>(department, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/departments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDepartmentList() {
    	ArrayList<Department> list = departmentRepository.findAll();
    	if(list.isEmpty()) return new ResponseEntity<String>("Not Found Any Department", HttpStatus.NOT_FOUND);
    	return new ResponseEntity<ArrayList<Department>>(list, HttpStatus.OK);
    }
}
