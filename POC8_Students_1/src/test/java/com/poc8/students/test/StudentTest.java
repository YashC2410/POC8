package com.poc8.students.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc8.students.Project;
import com.poc8.students.ResponseModule;
import com.poc8.students.Student;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class StudentTest {
private MockMvc mockMvc;
@Autowired
private WebApplicationContext context;

ObjectMapper objectMapper=new ObjectMapper();

@BeforeAll
public void setup() {
	mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
}
@Test
void createStudentTest() throws Exception{
	 File f = new File("C:\\Users\\user\\Downloads\\male.png");
	 FileInputStream fi1 = new FileInputStream(f);
	 MockMultipartFile fstmp = new MockMultipartFile("image", f.getName(), "multipart/form-data",fi1);
	 MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	 mockMvc.perform(MockMvcRequestBuilders.multipart("/createStudent").file(fstmp).param("id","21").param("fname", "Nitin").param("lname", "Yadav").param("email", "nitin2302@gmail.com")
			 .param("contact", "9134567879").contentType(MediaType.MULTIPART_FORM_DATA)).andDo(print()).andExpect(redirectedUrl("/"));
	
}
@Test
void updateStudentTest() throws Exception{
	 File f = new File("C:\\Users\\user\\Pictures\\Yash Chowdhary.jpg");
	 FileInputStream fi1 = new FileInputStream(f);
	 MockMultipartFile fstmp = new MockMultipartFile("image", f.getName(), "multipart/form-data",fi1);
	 MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	 mockMvc.perform(MockMvcRequestBuilders.multipart("/students/updateStudent").file(fstmp).param("id","3").param("fname", "Yash").param("lname", "Chowdhary").param("email", "yashneosoft@gmail.com")
			 .param("contact", "7977109376").contentType(MediaType.MULTIPART_FORM_DATA)).andDo(print()).andExpect(redirectedUrl(null));
	
}
@Test
void getAllStudent() throws Exception{
	MvcResult result = mockMvc.perform(get("/students/getAllStudents").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
			.andExpect(status().isOk()).andReturn();
	String resultContext = result.getResponse().getContentAsString();
	ResponseModule response = objectMapper.readValue(resultContext, ResponseModule.class);
	Assertions.assertTrue(response.isStatus());
	Assertions.assertEquals("Success",response.getProgressMessage());
}
@Test
void deleteStudentById() throws Exception{
	MvcResult result =  mockMvc.perform(delete("/students/deleteStudent/18").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).
		    andExpect(status().isOk()).andReturn(); 
			String resultContext=result.getResponse().getContentAsString(); 
		    ResponseModule response=objectMapper.readValue(resultContext, ResponseModule.class);
		    Assertions.assertTrue(response.isStatus());
		    Assertions.assertEquals("Success",response.getProgressMessage()); 
}
@Test
void getStudentById() throws Exception{
	MvcResult result = mockMvc.perform(get("/students/getStudentById/2").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
	.andExpect(status().isOk()).andReturn();
	String resultContext = result.getResponse().getContentAsString();
	ResponseModule response = objectMapper.readValue(resultContext, ResponseModule.class);
	Assertions.assertTrue(response.isStatus());
	Assertions.assertEquals("Success",response.getProgressMessage());
}
@Test
void getAllProjects() throws Exception {
	MvcResult result = mockMvc.perform(get("/students/getProjectTest/2").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
			.andExpect(status().isOk()).andReturn();
	String resultContext = result.getResponse().getContentAsString();
	ResponseModule response = objectMapper.readValue(resultContext, ResponseModule.class);
	Assertions.assertTrue(response.isStatus());
	Assertions.assertEquals("Success",response.getProgressMessage());
}
@Test
void createProject() throws Exception{
	Project project=new Project();
	project.setProjectName("DX Ball Game App");
	project.setProjectTech("Java,Spring Boot,Rest Web Services,JavaFX,Applet");
	String JsonRequest= objectMapper.writeValueAsString(project);
	MvcResult result=mockMvc.perform(post("/students/19/createProjectTest").content(JsonRequest).contentType(
	MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk()).
	andReturn();
	String resultContext =result.getResponse().getContentAsString(); 
	ResponseModule response=objectMapper.readValue(resultContext, ResponseModule.class);
	Assertions.assertTrue(response.isStatus());
	Assertions.assertEquals("Success",response.getProgressMessage());
}
@Test
void updateProject() throws Exception{
	Project project=new Project();
	project.setProjectName("Spring Microservice Currency Application");
	project.setProjectTech("Java,Dev Tools,Microservices,Cloud,Startup,Web Services,Testing , Spring Boot Start");
	String JsonRequest= objectMapper.writeValueAsString(project);
	MvcResult result=mockMvc.perform(put("/students/3/updateProjectTest/2").content(JsonRequest).contentType(
	MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk()).
	andReturn();
	String resultContext =result.getResponse().getContentAsString(); 
	ResponseModule response=objectMapper.readValue(resultContext, ResponseModule.class);
	Assertions.assertTrue(response.isStatus());
	Assertions.assertEquals("Success",response.getProgressMessage());
}
@Test
void deleteProject() throws Exception{
	MvcResult result =  mockMvc.perform(delete("/students/4/deleteProjectTest/9").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).
		    andExpect(status().isOk()).andReturn(); 
			String resultContext=result.getResponse().getContentAsString(); 
		    ResponseModule response=objectMapper.readValue(resultContext, ResponseModule.class);
		    Assertions.assertTrue(response.isStatus());
		    Assertions.assertEquals("Success",response.getProgressMessage()); 
}
}
