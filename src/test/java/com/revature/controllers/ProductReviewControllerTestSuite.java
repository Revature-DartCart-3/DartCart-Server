package com.revature.controllers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.driver.DartCartApplication;
import com.revature.models.Product;
import com.revature.models.ProductReview;
import com.revature.models.User;
import com.revature.repositories.ProductReviewRepo;
import com.revature.services.ProductReviewService;
import com.revature.services.ProductServiceImpl;
import com.revature.services.UserServiceImpl;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = DartCartApplication.class)
public class ProductReviewControllerTestSuite {

    @MockBean
    ProductReviewService mockProductReviewService;

    @MockBean
    UserServiceImpl mockUserService;

    @MockBean
    ProductServiceImpl mockProductService;

    @Autowired
    ProductReviewController sut;

    // @BeforeEach
    // void setup() {
    // mockProductReviewService = mock(ProductReviewService.class);
    // mockProductService = mock(ProductServiceImpl.class);
    // mockUserService = mock(UserServiceImpl.class);
    // sut = new ProductReviewController(
    // mockProductReviewService,
    // mockUserService,
    // mockProductService);
    // }

    @Test
    public void test_newProductReview_returns200_givenValidRequest() {
        mockProductReviewService = mock(ProductReviewService.class);
        mockProductService = mock(ProductServiceImpl.class);
        mockUserService = mock(UserServiceImpl.class);
        sut = new ProductReviewController(
                mockProductReviewService,
                mockUserService,
                mockProductService);
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        Product product = new Product();
        product.setId(1);
        ProductReview pr = new ProductReview(1, "valid", "valid", 3, user, product);
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("username");
        when(mockUserService.getUserByUsername("username")).thenReturn(user);
        when(mockProductService.getProductById(1)).thenReturn(Optional.of(product));
        when(mockProductReviewService.findProductReviewByUserAndProduct(user, product)).thenReturn(null);
        when(mockProductReviewService.addProductReview(pr)).thenReturn(pr);

        ResponseEntity<ProductReview> responseEntity = sut.newProductReview(pr, auth, 1);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void test_newProductReview_returns400_givenInvalidRequest() {
        // TODO throws exception
        mockProductReviewService = mock(ProductReviewService.class);
        mockProductService = mock(ProductServiceImpl.class);
        mockUserService = mock(UserServiceImpl.class);
        sut = new ProductReviewController(
                mockProductReviewService,
                mockUserService,
                mockProductService);
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        Product product = new Product();
        product.setId(1);
        ProductReview pr = new ProductReview(1, "valid", "valid", 3, user, product);
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("username");
        when(mockUserService.getUserByUsername("username")).thenReturn(user);
        when(mockProductService.getProductById(1)).thenReturn(null);
        when(mockProductReviewService.findProductReviewByUserAndProduct(user, product)).thenReturn(null);
        when(mockProductReviewService.addProductReview(pr)).thenReturn(pr);

        ResponseEntity<ProductReview> responseEntity = sut.newProductReview(pr, auth, 1);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void test_newProductReview_returns403_givenDuplicateProductReview() {
        mockProductReviewService = mock(ProductReviewService.class);
        mockProductService = mock(ProductServiceImpl.class);
        mockUserService = mock(UserServiceImpl.class);
        sut = new ProductReviewController(
                mockProductReviewService,
                mockUserService,
                mockProductService);
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        Product product = new Product();
        product.setId(1);
        ProductReview pr = new ProductReview(1, "valid", "valid", 3, user, product);
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("username");
        when(mockUserService.getUserByUsername("username")).thenReturn(user);
        when(mockProductService.getProductById(1)).thenReturn(Optional.of(product));
        when(mockProductReviewService.findProductReviewByUserAndProduct(user, product)).thenReturn(pr);
        when(mockProductReviewService.addProductReview(pr)).thenReturn(pr);

        ResponseEntity<ProductReview> responseEntity = sut.newProductReview(pr, auth, 1);

        Assertions.assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void test_newProductReview_returns406_givenProductId() {
        mockProductReviewService = mock(ProductReviewService.class);
        mockProductService = mock(ProductServiceImpl.class);
        mockUserService = mock(UserServiceImpl.class);
        sut = new ProductReviewController(
                mockProductReviewService,
                mockUserService,
                mockProductService);
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        Product product = new Product();
        product.setId(1);
        ProductReview pr = new ProductReview(1, "valid", "valid", 3, user, product);
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("username");
        when(mockUserService.getUserByUsername("username")).thenReturn(user);
        // when(mockProductService.getProductById(1)).thenReturn(null);
        when(mockProductReviewService.findProductReviewByUserAndProduct(user, product)).thenReturn(null);
        when(mockProductReviewService.addProductReview(pr)).thenReturn(pr);

        ResponseEntity<ProductReview> responseEntity = sut.newProductReview(pr, auth, 1);

        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
    }

    @Test
    public void test_newProductReview_returns500_onServerError() {
        mockProductReviewService = mock(ProductReviewService.class);
        mockProductService = mock(ProductServiceImpl.class);
        mockUserService = mock(UserServiceImpl.class);
        sut = new ProductReviewController(
                mockProductReviewService,
                mockUserService,
                mockProductService);
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        Product product = new Product();
        product.setId(1);
        ProductReview pr = new ProductReview(1, "valid", "valid", 3, user, product);
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("username");
        when(mockUserService.getUserByUsername("username")).thenReturn(user);
        when(mockProductService.getProductById(1)).thenReturn(Optional.of(product));
        when(mockProductReviewService.findProductReviewByUserAndProduct(user, product)).thenReturn(null);
        when(mockProductReviewService.addProductReview(pr)).thenReturn(null);

        ResponseEntity<ProductReview> responseEntity = sut.newProductReview(pr, auth, 1);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void test_getProductReviews_returnsList_givenValidRequest() {
        mockProductReviewService = mock(ProductReviewService.class);
        mockProductService = mock(ProductServiceImpl.class);
        mockUserService = mock(UserServiceImpl.class);
        sut = new ProductReviewController(
                mockProductReviewService,
                mockUserService,
                mockProductService);
        List<ProductReview> reviews = new ArrayList<>();
        when(mockProductReviewService.findAllProductReviews()).thenReturn(reviews);

        ResponseEntity<List> responseEntity = sut.getProductReviews();

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void test_getProductReviews_returns400_givenInvalidRequest() {
        mockProductReviewService = mock(ProductReviewService.class);
        mockProductService = mock(ProductServiceImpl.class);
        mockUserService = mock(UserServiceImpl.class);
        sut = new ProductReviewController(
                mockProductReviewService,
                mockUserService,
                mockProductService);
        when(mockProductReviewService.findAllProductReviews()).thenThrow(new RuntimeException());
        
        ResponseEntity<List> responseEntity = sut.getProductReviews();

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void test_getProductReviewByUser_returnsList_givenValidRequest() {
        mockProductReviewService = mock(ProductReviewService.class);
        mockProductService = mock(ProductServiceImpl.class);
        mockUserService = mock(UserServiceImpl.class);
        sut = new ProductReviewController(
                mockProductReviewService,
                mockUserService,
                mockProductService);
        User user = new User();
        user.setId(1);
        List<ProductReview> reviews = new ArrayList<>();
        when(mockUserService.getUserById(1)).thenReturn(Optional.of(user));
        when(mockProductReviewService.findAllProductReviewsByUser(user)).thenReturn(reviews);

        ResponseEntity<List> responseEntity = sut.getProductReviewByUser(1);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void test_getProductReviewByUser_returns400_givenInvalidRequest() {
        mockProductReviewService = mock(ProductReviewService.class);
        mockProductService = mock(ProductServiceImpl.class);
        mockUserService = mock(UserServiceImpl.class);
        sut = new ProductReviewController(
                mockProductReviewService,
                mockUserService,
                mockProductService);
        User user = new User();
        user.setId(1);
        when(mockUserService.getUserById(1)).thenReturn(null);
        //when(mockProductReviewService.findAllProductReviewsByUser(user)).thenThrow(new RuntimeException());
        ResponseEntity<List> responseEntity = sut.getProductReviewByUser(1);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
    @Test
    public void test_getProductReviewByProduct_returnsList_givenValidRequest() {
        mockProductReviewService = mock(ProductReviewService.class);
        mockProductService = mock(ProductServiceImpl.class);
        mockUserService = mock(UserServiceImpl.class);
        sut = new ProductReviewController(
                mockProductReviewService,
                mockUserService,
                mockProductService);
        Product product = new Product();
        product.setId(1);
        List<ProductReview> reviews = new ArrayList<>();
        when(mockProductService.getProductById(1)).thenReturn(Optional.of(product));
        when(mockProductReviewService.findAllProductReviewsByProduct(product)).thenReturn(reviews);

        ResponseEntity<List> responseEntity = sut.getProductReviewByProduct(1);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void test_getProductReviewByProduct_returns400_givenInvalidRequest() {
        mockProductReviewService = mock(ProductReviewService.class);
        mockProductService = mock(ProductServiceImpl.class);
        mockUserService = mock(UserServiceImpl.class);
        sut = new ProductReviewController(
                mockProductReviewService,
                mockUserService,
                mockProductService);
        Product product = new Product();
        product.setId(1);
        when(mockProductService.getProductById(1)).thenReturn(null);
        //when(mockProductReviewService.findAllProductReviewsByProduct(product)).thenThrow(new RuntimeException());
        ResponseEntity<List> responseEntity = sut.getProductReviewByProduct(1);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
