package com.revature.controllers;

import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.driver.DartCartApplication;
import com.revature.models.User;
import com.revature.services.UserServiceImpl;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = DartCartApplication.class
		)
public class UserProfileControllerTest {
	
	private MockMvc mvc;
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	  private WebApplicationContext webApplicationContext;
	
	@MockBean
	private UserServiceImpl mockUserService;
	
	private final User mockUser = new User(
		    1,
		    "test1",
		    "password",
		    "Test",
		    "User",
		    "test1@dartcart.net",
		    "123-456-7890",
		    "1 Test Street, Test Town, Testonia 12345",
		    123563672L,
		    null, null, null, null
		  );
	
	@BeforeEach
	void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void test_getUserByToken() throws Exception {
		UsernamePasswordAuthenticationToken Auth = new UsernamePasswordAuthenticationToken("test1", "password");
		Optional<User> oUser = Optional.ofNullable(mockUser);
		
		when(mockUserService.getUserByUsername("test1")).thenReturn(mockUser);
		when(mockUserService.getUserById(1)).thenReturn(oUser);
	
		mvc.perform(MockMvcRequestBuilders.get("/getProfile")
				.principal(Auth)
		)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void test_getUserByToken_inValidUser() throws Exception {
		UsernamePasswordAuthenticationToken Auth = new UsernamePasswordAuthenticationToken("test1", "password");
		Optional<User> oUser = Optional.empty();
		
		when(mockUserService.getUserByUsername("test1")).thenReturn(mockUser);
		when(mockUserService.getUserById(1)).thenReturn(oUser);
	
		mvc.perform(MockMvcRequestBuilders.get("/getProfile")
				.principal(Auth)
		)
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void test_updateProfile() throws JsonProcessingException, Exception {
		User oldUser = new User(); 
		oldUser.setId(5); oldUser.setUsername("username"); oldUser.setFirstName("firstname"); oldUser.setLastName("lastname"); 
		oldUser.setEmail("test@email.com"); oldUser.setPhone("123-456-7890"); oldUser.setLocation("1 street city, state 01234"); 
		oldUser.setAboutMe("oldAboutMe"); oldUser.setImageURL("Image"); oldUser.setPassword("password");
		
		User newUser = new User();
		newUser.setId(5); newUser.setUsername("updateusername"); newUser.setFirstName("updatefirstname"); newUser.setLastName("updatelastname"); 
		newUser.setEmail("updatetest@email.com"); newUser.setPhone("update123-456-7890"); newUser.setLocation("update1 street city, state 01234");
		newUser.setAboutMe("updateoldAboutMe"); newUser.setImageURL("UpdateImage"); newUser.setPassword("password");
		
		Optional<User> opOldUser = Optional.of(oldUser);
		
		UsernamePasswordAuthenticationToken Auth = new UsernamePasswordAuthenticationToken("username", "password");
		Optional<User> oUser = Optional.ofNullable(mockUser);
		
		when(mockUserService.getUserByUsername("username")).thenReturn(oldUser);
		when(mockUserService.getUserById(5)).thenReturn(opOldUser);
		
		
		mvc.perform(MockMvcRequestBuilders.patch("/updateProfile")
				.principal(Auth)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newUser))
		)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
