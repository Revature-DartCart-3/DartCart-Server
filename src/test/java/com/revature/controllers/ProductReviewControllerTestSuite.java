package com.revature.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
import com.revature.models.Product;
import com.revature.models.ProductReview;
import com.revature.models.User;
import com.revature.services.ProductReviewService;
import com.revature.services.ProductServiceImpl;
import com.revature.services.UserServiceImpl;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = DartCartApplication.class
		)
public class ProductReviewControllerTestSuite {

    private MockMvc mvc;
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @MockBean
    private ProductReviewService mockProductReviewService;
    
    @MockBean
    private UserServiceImpl mockUserService;
    
    @MockBean
    private ProductServiceImpl mockProductService;
    
    @BeforeEach
    public void setup() {
    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test                                                                                          //             newProductReview
    public void test_newProductReview() throws JsonProcessingException, Exception {
    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test1", "password");
    	User testUser = new User(1);
    	
    	ProductReview productReview = new ProductReview();
    	
    	Product prod = new Product(); prod.setId(2);
    	Optional<Product> oprod = Optional.ofNullable(prod);
    	
    	when(mockUserService.getUserByUsername("test1")).thenReturn(testUser);
    	when(mockProductService.getProductById(2)).thenReturn(oprod); //Return prod
    	when(mockProductReviewService.findProductReviewByUserAndProduct(null, null)).thenReturn(null);
    	productReview.setUser(testUser); productReview.setProduct(prod);
    	when(mockProductReviewService.addProductReview(productReview)).thenReturn(productReview);
    	
    	mvc.perform(MockMvcRequestBuilders
    				.post("/create-product-review/product/{id}", 2)
    				.principal(auth)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(mapper.writeValueAsString(productReview))
    			   )
    	.andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void test_newProductReview_emptyProduct() throws JsonProcessingException, Exception {
    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test1", "password");
    	User testUser = new User(1);
    	
    	ProductReview productReview = new ProductReview();
    	Product prod = new Product(); prod.setId(2);
    	Optional<Product> oprod = Optional.empty();
    	
    	when(mockUserService.getUserByUsername("test1")).thenReturn(testUser);
    	when(mockProductService.getProductById(2)).thenReturn(oprod); //Return prod
    	
    	mvc.perform(MockMvcRequestBuilders
    				.post("/create-product-review/product/{id}", 2)
    				.principal(auth)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(mapper.writeValueAsString(productReview))
    			   )
    	.andExpect(MockMvcResultMatchers.status().isNotAcceptable());
    }
    
    @Test
    public void test_newProductReview_revProductReview_isNotNull() throws JsonProcessingException, Exception {
    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test1", "password");
    	User testUser = new User(1);
    	Product prod = new Product(); prod.setId(2);
    	
    	ProductReview productReview = new ProductReview(); productReview.setUser(testUser); productReview.setProduct(prod);
    	
    	Optional<Product> oprod = Optional.ofNullable(prod);
    	
    	when(mockUserService.getUserByUsername("test1")).thenReturn(testUser);
    	when(mockProductService.getProductById(2)).thenReturn(oprod); //Return prod
    	when(mockProductReviewService.findProductReviewByUserAndProduct(testUser, prod)).thenReturn(productReview);
    	
    	mvc.perform(MockMvcRequestBuilders
    				.post("/create-product-review/product/{id}", 2)
    				.principal(auth)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(mapper.writeValueAsString(productReview))
    			   )
    	.andExpect(MockMvcResultMatchers.status().isForbidden());
    }
    
    @Test
    public void test_newProductReview_createdReview_isNull() throws JsonProcessingException, Exception {
    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test1", "password");
    	User testUser = new User(1);
    	
    	ProductReview productReview = new ProductReview();
    	
    	Product prod = new Product(); prod.setId(2);
    	Optional<Product> oprod = Optional.ofNullable(prod);
    	
    	when(mockUserService.getUserByUsername("test1")).thenReturn(testUser);
    	when(mockProductService.getProductById(2)).thenReturn(oprod); //Return prod
    	when(mockProductReviewService.findProductReviewByUserAndProduct(null, null)).thenReturn(null);
    	when(mockProductReviewService.addProductReview(productReview)).thenReturn(null);
    	
    	mvc.perform(MockMvcRequestBuilders
    				.post("/create-product-review/product/{id}", 2)
    				.principal(auth)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(mapper.writeValueAsString(productReview))
    			   )
    	.andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
    
    @Test
    public void test_newProductReview_ThrowsError() throws JsonProcessingException, Exception {
    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test1", "password");
    	User testUser = new User(1);
    	
    	ProductReview productReview = new ProductReview();
    	
    	Product prod = new Product(); prod.setId(2);
    	Optional<Product> oprod = Optional.ofNullable(prod);
    	
    	when(mockUserService.getUserByUsername("test1")).thenReturn(testUser);
    	when(mockProductService.getProductById(2)).thenReturn(oprod); //Return prod
    	when(mockProductReviewService.findProductReviewByUserAndProduct(null, null)).thenReturn(null);
    	productReview.setUser(testUser); productReview.setProduct(prod);
    	when(mockProductReviewService.addProductReview(productReview)).thenThrow(RuntimeException.class);
    	
    	mvc.perform(MockMvcRequestBuilders
    				.post("/create-product-review/product/{id}", 2)
    				.principal(auth)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(mapper.writeValueAsString(productReview))
    			   )
    	.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }                               																	//  End newProductReview
    
    @Test																								//  getProductReviews
    public void test_getProductReviews() throws Exception {
    	List<ProductReview> prodList = new ArrayList<>();
    	when(mockProductReviewService.findAllProductReviews()).thenReturn(prodList);
    	
    	mvc.perform(MockMvcRequestBuilders
				.get("/product-reviews")
			   )
	.andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void test_getProductReviews_badRequest() throws Exception {
    	List<ProductReview> prodList = new ArrayList<>();
    	when(mockProductReviewService.findAllProductReviews()).thenThrow(RuntimeException.class);
    	
    	mvc.perform(MockMvcRequestBuilders
				.get("/product-reviews")
			   )
	.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }																												//End  getProductReviews
    
    @Test																								//  getProductReviewByUser
    public void test_getProductReviewByUser() throws Exception {
    	List<ProductReview> prodList = new ArrayList<>();
    	User user = new User(2);
    	Optional<User> ouser = Optional.ofNullable(user);
    	
    	when(mockUserService.getUserById(2)).thenReturn(ouser);
    	when(mockProductReviewService.findAllProductReviewsByUser(user)).thenReturn(prodList);
    	
    	mvc.perform(MockMvcRequestBuilders
				.get("/product-reviews/user/{id}", 2)
			   )
	.andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void test_getProductReviewByUser_badRequest() throws Exception {
    	List<ProductReview> prodList = new ArrayList<>();
    	User user = new User(2);
    	Optional<User> ouser = Optional.ofNullable(user);
    	
    	when(mockUserService.getUserById(2)).thenReturn(ouser);
    	when(mockProductReviewService.findAllProductReviewsByUser(user)).thenThrow(RuntimeException.class);
    	
    	mvc.perform(MockMvcRequestBuilders
				.get("/product-reviews/user/{id}", 2)
			   )
	.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }																									// End getProductReviewByUser
    
    @Test																								//  getProductReviewByProduct
    public void test_getProductReviewByProduct() throws Exception {
    	List<ProductReview> prodList = new ArrayList<>();
    	
    	Product prod = new Product();
    	Optional<Product> oprod = Optional.ofNullable(prod);
    	
    	when(mockProductService.getProductById(2)).thenReturn(oprod);
    	when(mockProductReviewService.findAllProductReviewsByProduct(prod)).thenReturn(prodList);
    	
    	mvc.perform(MockMvcRequestBuilders
				.get("/product-reviews/product/{id}", 2)
			   )
	.andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void test_getProductReviewByProduct_badRequest() throws Exception {
List<ProductReview> prodList = new ArrayList<>();
    	
    	Product prod = new Product();
    	Optional<Product> oprod = Optional.ofNullable(prod);
    	
    	when(mockProductService.getProductById(2)).thenReturn(oprod);
    	when(mockProductReviewService.findAllProductReviewsByProduct(prod)).thenThrow(RuntimeException.class);
    	
    	mvc.perform(MockMvcRequestBuilders
				.get("/product-reviews/product/{id}", 2)
			   )
	.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }																								//End  getProductReviewByProduct
    
    @Test																								//   updateProductReview
    public void test_updateProductReview() throws JsonProcessingException, Exception {
    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test1", "password");
    	
    	User user = new User(1); user.setUsername("test1");
    	
    	Product prod = new Product(); prod.setId(5);
    	Optional oprod = Optional.ofNullable(prod);
    	
    	ProductReview productReview = new ProductReview(); productReview.setId(3); productReview.setUser(user);
    	
    	when(mockUserService.getUserByUsername("test1")).thenReturn(user);
    	productReview.setUser(user);
    	when(mockProductService.getProductById(5)).thenReturn(oprod);
    	productReview.setProduct(prod);
    	when(mockProductReviewService.findProductReviewById(3)).thenReturn(productReview);
    	
    	when(mockProductReviewService.updateProductReview(productReview)).thenReturn(true);
    	
    	mvc.perform(MockMvcRequestBuilders
    				.put("/update-product-review/product/{id}", 5)
    				.principal(auth)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(mapper.writeValueAsString(productReview))
    			   )
    				.andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void test_updateProductReview_Not_Acceptable() throws JsonProcessingException, Exception {
    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test1", "password");
    	
    	User user = new User(1);
    	
    	Product prod = new Product();
    	Optional oprod = Optional.empty();
    	
    	ProductReview productReview = new ProductReview();
    	
    	when(mockUserService.getUserByUsername("test1")).thenReturn(user);
    	when(mockProductService.getProductById(2)).thenReturn(oprod);
    	
    	mvc.perform(MockMvcRequestBuilders
    				.put("/update-product-review/product/{id}", 2)
    				.principal(auth)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(mapper.writeValueAsString(productReview))
    			   )
    				.andExpect(MockMvcResultMatchers.status().isNotAcceptable());
    }
    
    @Test
    public void test_updateProductReview_Unauthorized() throws JsonProcessingException, Exception {
    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test1", "password");
    	
    	User user = new User(2);
    	User notUser = new User(3); notUser.setUsername("test1");
    	
    	Product prod = new Product(); prod.setId(5);
    	Optional oprod = Optional.ofNullable(prod);
    	
    	ProductReview productReview = new ProductReview(); productReview.setId(1);
    	
    	when(mockUserService.getUserByUsername("test1")).thenReturn(notUser);
    	productReview.setUser(user);
    	when(mockProductService.getProductById(5)).thenReturn(oprod);
    	productReview.setProduct(prod);
    	when(mockProductReviewService.findProductReviewById(1)).thenReturn(productReview);
    	
    	mvc.perform(MockMvcRequestBuilders
    				.put("/update-product-review/product/{id}", 5)
    				.principal(auth)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(mapper.writeValueAsString(productReview))
    			   )
    				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    
    @Test
    public void test_updateProductReview_InternalServerError() throws JsonProcessingException, Exception {
    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test1", "password");
    	
    	User user = new User(1); user.setUsername("test1");
    	
    	Product prod = new Product(); prod.setId(3);
    	Optional oprod = Optional.ofNullable(prod);
    	
    	ProductReview productReview = new ProductReview(); productReview.setId(5);
    	
    	when(mockUserService.getUserByUsername("test1")).thenReturn(user);
    	productReview.setUser(user);
    	when(mockProductService.getProductById(3)).thenReturn(oprod);
    	productReview.setProduct(prod);
    	when(mockProductReviewService.findProductReviewById(5)).thenReturn(productReview);
    	
    	when(mockProductReviewService.updateProductReview(productReview)).thenReturn(false);
    	
    	mvc.perform(MockMvcRequestBuilders
    				.put("/update-product-review/product/{id}", 3)
    				.principal(auth)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(mapper.writeValueAsString(productReview))
    			   )
    				.andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
    
    @Test
    public void test_updateProductReview_ThrowsException() throws JsonProcessingException, Exception {
    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test1", "password");
    	
    	User user = new User(1);
    	
    	Product prod = new Product();
    	Optional oprod = Optional.ofNullable(prod);
    	
    	ProductReview productReview = new ProductReview(); productReview.setId(5); productReview.setUser(user);
    	
    	when(mockUserService.getUserByUsername("test1")).thenReturn(user);
    	when(mockProductService.getProductById(2)).thenReturn(oprod);
    	when(mockProductReviewService.findProductReviewById(1)).thenReturn(productReview);
    	when(mockProductReviewService.updateProductReview(productReview)).thenThrow(RuntimeException.class);
    	
    	mvc.perform(MockMvcRequestBuilders
    				.put("/update-product-review/product/{id}", 2)
    				.principal(auth)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(mapper.writeValueAsString(productReview))
    			   )
    				.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }																								//  End updateProductReview
    
    @Test																								//   deleteProductReview
    public void test_deleteProductReview() throws JsonProcessingException, Exception {
    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test1", "password");
    	
    	User user = new User(1); user.setUsername("test1");
    	
    	Product prod = new Product(); prod.setId(5);
    	Optional oprod = Optional.ofNullable(prod);
    	
    	ProductReview productReview = new ProductReview(); productReview.setId(3);
    	
    	when(mockUserService.getUserByUsername("test1")).thenReturn(user);
    	productReview.setUser(user);
    	when(mockProductService.getProductById(5)).thenReturn(oprod);
    	productReview.setProduct(prod);
    	when(mockProductReviewService.findProductReviewById(3)).thenReturn(productReview);
    	
    	when(mockProductReviewService.deleteProductReview(productReview)).thenReturn(true);
    	
    	mvc.perform(MockMvcRequestBuilders
    				.delete("/delete-product-review/product/{id}", 5)
    				.principal(auth)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(mapper.writeValueAsString(productReview))
    			   )
    				.andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void test_deleteProductReview_ProductEmpty_NotAcceptable() throws JsonProcessingException, Exception {
    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test1", "password");
    	
    	User user = new User(1); user.setUsername("test1");
    	
    	Product prod = new Product(); prod.setId(5);
    	Optional oprod = Optional.empty();
    	
    	ProductReview productReview = new ProductReview(); productReview.setId(3); productReview.setUser(user);
    	
    	when(mockUserService.getUserByUsername("test1")).thenReturn(user);
    	productReview.setUser(user);
    	when(mockProductService.getProductById(5)).thenReturn(oprod);
    	productReview.setProduct(prod);
    	when(mockProductReviewService.findProductReviewById(3)).thenReturn(productReview);
    	
    	when(mockProductReviewService.deleteProductReview(productReview)).thenReturn(true);
    	
    	mvc.perform(MockMvcRequestBuilders
    				.delete("/delete-product-review/product/{id}", 5)
    				.principal(auth)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(mapper.writeValueAsString(productReview))
    			   )
    				.andExpect(MockMvcResultMatchers.status().isNotAcceptable());
    }
    
    @Test
    public void test_deleteProductReview_Unauthorized() throws JsonProcessingException, Exception {
    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test1", "password");
    	
    	User user = new User(1);
    	User notUser = new User(3); notUser.setUsername("test1");
    	
    	Product prod = new Product(); prod.setId(5);
    	Optional oprod = Optional.ofNullable(prod);
    	
    	ProductReview productReview = new ProductReview(); productReview.setId(3);
    	
    	when(mockUserService.getUserByUsername("test1")).thenReturn(notUser);
    	productReview.setUser(user);
    	when(mockProductService.getProductById(5)).thenReturn(oprod);
    	productReview.setProduct(prod);
    	when(mockProductReviewService.findProductReviewById(3)).thenReturn(productReview);
    	
    	when(mockProductReviewService.deleteProductReview(productReview)).thenReturn(true);
    	
    	mvc.perform(MockMvcRequestBuilders
    				.delete("/delete-product-review/product/{id}", 5)
    				.principal(auth)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(mapper.writeValueAsString(productReview))
    			   )
    				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    
    @Test
    public void test_deleteProductReview_InternalServerError() throws JsonProcessingException, Exception {
    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test1", "password");
    	
    	User user = new User(1); user.setUsername("test1");
    	
    	Product prod = new Product(); prod.setId(5);
    	Optional oprod = Optional.ofNullable(prod);
    	
    	ProductReview productReview = new ProductReview(); productReview.setId(3);
    	
    	when(mockUserService.getUserByUsername("test1")).thenReturn(user);
    	productReview.setUser(user);
    	when(mockProductService.getProductById(5)).thenReturn(oprod);
    	productReview.setProduct(prod);
    	when(mockProductReviewService.findProductReviewById(3)).thenReturn(productReview);
    	
    	when(mockProductReviewService.deleteProductReview(productReview)).thenReturn(false);
    	
    	mvc.perform(MockMvcRequestBuilders
    				.delete("/delete-product-review/product/{id}", 5)
    				.principal(auth)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(mapper.writeValueAsString(productReview))
    			   )
    				.andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
    
    @Test
    public void test_deleteProductReview_BadRequest() throws JsonProcessingException, Exception {
    	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test1", "password");
    	
    	User user = new User(1); user.setUsername("test1");
    	
    	Product prod = new Product(); prod.setId(5);
    	Optional oprod = Optional.ofNullable(prod);
    	
    	ProductReview productReview = new ProductReview(); productReview.setId(3);
    	
    	when(mockUserService.getUserByUsername("test1")).thenReturn(user);
    	productReview.setUser(user);
    	when(mockProductService.getProductById(5)).thenReturn(oprod);
    	productReview.setProduct(prod);
    	when(mockProductReviewService.findProductReviewById(3)).thenReturn(productReview);
    	
    	when(mockProductReviewService.deleteProductReview(productReview)).thenThrow(RuntimeException.class);
    	
    	mvc.perform(MockMvcRequestBuilders
    				.delete("/delete-product-review/product/{id}", 5)
    				.principal(auth)
    				.contentType(MediaType.APPLICATION_JSON)
    				.content(mapper.writeValueAsString(productReview))
    			   )
    				.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }																								//  End deleteProductReview
    
}
