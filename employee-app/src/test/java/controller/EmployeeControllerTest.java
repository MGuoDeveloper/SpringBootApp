package controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import employee.Application;
import employee.respository.*;

import java.math.BigDecimal;
import java.util.*;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class EmployeeControllerTest {
private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
	
	@Test
	public void testWelcome() throws Exception {  // "/emp"  ---- Employee List
		mockMvc.perform(get("/emp"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("WEB-INF/views/index.jsp"));
	}
	
	@Test
	public void testNewEmp() throws Exception {  // "/newemployee"  ---- Create New Employee
		mockMvc.perform(get("/newemployee"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("WEB-INF/views/create.jsp"));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testGetEmployeeById() throws Exception {  // "/employee/{id}" ---- Get Employee By Id
        Employee emp = employeeRepository.save(new Employee(0, "Jake", "Mike", 1, new BigDecimal(60000)));
        int emp_id = emp.getId();
		MvcResult result = mockMvc.perform(get("/employee/"+emp_id))
			.andExpect(status().isOk())
			.andReturn();
		String res = result.getResponse().getContentAsString();
		assertEquals("{\"id\":"+ emp_id +",\"name\":\"Jake\",\"manager_name\":\"Mike\",\"dept\":1,\"salary\":60000}", res);
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testGetEmployees() throws Exception {
		mockMvc.perform(get("/employees"))
			.andExpect(status().isOk());
	}
	
}
