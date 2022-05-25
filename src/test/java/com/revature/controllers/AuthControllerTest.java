package com.revature.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.driver.DartCartApplication;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(classes = DartCartApplication.class)
public class AuthControllerTest {
 @Autowired
 MockMvc mockMvc;

 @Test
 public void testLoginHappy() throws Exception {
   String url = "/login";
   User user = new User("test", "test", new ArrayList<>());
   //... more
   ObjectMapper mapper = new ObjectMapper();
   mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
   ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
   String requestJson = ow.writeValueAsString(user);

   mockMvc
     .perform(
       post(url).contentType(APPLICATION_JSON_UTF8).content(requestJson)
     )
     .andExpect(status().isOk());
 }

 @Test
 public void testLoginUnhappy() throws Exception {
   String url = "/login";
   User user = new User("test", "test1", new ArrayList<>());
   //... more
   ObjectMapper mapper = new ObjectMapper();
   mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
   ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
   String requestJson = ow.writeValueAsString(user);

   mockMvc
     .perform(
       post(url).contentType(APPLICATION_JSON_UTF8).content(requestJson)
     )
     .andExpect(status().isUnauthorized());
 }
}
