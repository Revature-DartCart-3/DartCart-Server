package com.revature.services;

import com.revature.driver.DartCartApplication;
import com.revature.models.AccountType;
import com.revature.models.User;
import com.revature.repositories.UserRepo;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(
 webEnvironment = SpringBootTest.WebEnvironment.MOCK,
 classes = DartCartApplication.class
)
class UserServiceTest {
 private final User mockUser = new User(
   1,
   "test1",
   "password",
   "Test",
   "User",
   "test1@DartCart.net",
   "123-456-7890",
   "1 Test Street, Test Town, Testonia 12345",
   123563672L,
   AccountType.user,
   null, null, null, null
 );

 @Autowired
 private WebApplicationContext webApplicationContext;

 @MockBean
 private UserRepo mockUserRepo;

 @Autowired
 private UserService mockUserService;

 @Autowired
 BCryptPasswordEncoder bCryptEncoder;

 @BeforeEach
 void setup() {
   MockMvc mvc = MockMvcBuilders
     .webAppContextSetup(webApplicationContext)
     .build();
 }

 @Test
 void givenUser_whenAddUser_thenAddNewUser() {
   User encrypted = mockUser;
   encrypted.setPassword(bCryptEncoder.encode(encrypted.getPassword()));
   Mockito.when(mockUserRepo.save(mockUser)).thenReturn(encrypted);
   Assertions.assertEquals(encrypted, mockUserService.addUser(mockUser));
 }

 @Test
 public void test_getUserById_validId() {
	  Optional<User> testUser = Optional.of(mockUser);
	  Mockito.when(mockUserRepo.findById(1)).thenReturn(testUser);
	  Assertions.assertEquals(testUser, mockUserService.getUserById(1));
 }

 @Test
 public void test_getAllUsers() {
	  List<User> testList = new ArrayList<>();
	  testList.add(mockUser);
	  Mockito.when(mockUserRepo.findAll()).thenReturn(testList);
	  Assertions.assertEquals(testList, mockUserService.getAllUsers());
 }

 @Test
 public void test_deleteUser_validID() {
	  boolean deleteTest = mockUserService.deleteUser(3);
	  verify(mockUserRepo).deleteById(3);
	  Assertions.assertTrue(deleteTest);
 }

 @Test
 public void test_deleteUser_invalidID() {
	  int nullId = -1;
	  Mockito.doThrow(new IllegalArgumentException()).when(mockUserRepo).deleteById(nullId);
	  boolean deleteTest = mockUserService.deleteUser(nullId);
	  Assertions.assertFalse(deleteTest);
 }

 @Test
 public void test_getUserByUsername_validUsername() {
	  String validUser = "test1";
	  Mockito.when(mockUserRepo.findByUsername(validUser)).thenReturn(mockUser);
	  Assertions.assertEquals(mockUser, mockUserService.getUserByUsername(validUser));
 }
}
