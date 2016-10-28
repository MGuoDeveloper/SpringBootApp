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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import department.Application;
import department.repository.Department;
import department.repository.DepartmentRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SuppressWarnings({"unused"})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class DepartmentControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
	
	@Test
	public void testWelcome() throws Exception {  // "/dep"  ---- Department List
		mockMvc.perform(get("/dep"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("WEB-INF/views/index.jsp"));
	}
	
	@Test
	public void testNewDep() throws Exception {  // "/newdepartment"  ---- Create New Department
		mockMvc.perform(get("/newdepartment"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("WEB-INF/views/create.jsp"));
	}
	
	/*@Transactional
	@Rollback(true)
	@Test
	public void testSaveDep() throws Exception {  // "/savedepartment"  ---- Save new department
		Department dep = new Department(0, "Board", new BigDecimal(20000), new BigDecimal(60000));
		MockHttpServletRequestBuilder builder = 
				post("/savedepartment")
					.requestAttr("department", dep)
					.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		MvcResult mr = mockMvc.perform(builder)
			.andExpect(status().isCreated())
			.andExpect(forwardedUrl("/dep"))
			.andReturn();
	}*/
	
	@Transactional
	@Rollback(true)
	@Test
	public void testGetDepartmentById() throws Exception {  // "/department/{id}" ---- Get Department By Id
        Department dep = departmentRepository.save(new Department(0, "Board", new BigDecimal(20000), new BigDecimal(60000)));
        int dep_id = dep.getId();
		MvcResult result = mockMvc.perform(get("/department/"+dep_id))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andReturn();
		String res = result.getResponse().getContentAsString();
		assertEquals("{\"id\":"+ dep_id +",\"name\":\"Board\",\"salary_min_range\":20000,\"salary_max_range\":60000}", res);
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testGetDepartments() throws Exception {
		mockMvc.perform(get("/departments"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
	}
	
}
