package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.models.Product;
import com.revature.models.ProductReview;
import com.revature.models.User;
import com.revature.services.ProductReviewService;
import com.revature.services.ProductServiceImpl;
import com.revature.services.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProductReviewController {
    private final ProductReviewService productReviewService;
    private final UserServiceImpl userService;
    private final ProductServiceImpl productService;

    @Autowired
    public ProductReviewController(ProductReviewService productReviewService, UserServiceImpl userService,
            ProductServiceImpl productService) {
        this.productReviewService = productReviewService;
        this.userService = userService;
        this.productService = productService;
    }

    @PostMapping("/create-product-review/product/{id}")
    public ResponseEntity<ProductReview> newProductReview(@RequestBody ProductReview productReview, Authentication auth,
            @PathVariable("id") int id) {
        try {
            User user = userService.getUserByUsername(auth.getName());
            productReview.setUser(user);
            Optional<Product> product = productService.getProductById(id);
            if(product.isPresent()){
                productReview.setProduct(product.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            // check for duplicate review (same product, same user)
            ProductReview prevProductReview = productReviewService
                    .findProductReviewByUserAndProduct(productReview.getUser(), productReview.getProduct());
            if (prevProductReview != null) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            ProductReview createdReview = productReviewService.addProductReview(productReview);
            if (createdReview == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(createdReview, HttpStatus.OK);
            }
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product-reviews")
    public ResponseEntity<List> getProductReviews() {
        try {
            List<ProductReview> reviews = productReviewService.findAllProductReviews();

            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product-reviews/user/{id}")
    public ResponseEntity<List> getProductReviewByUser(@PathVariable("id") int id) {
        try {
            User user = userService.getUserById(id).get();
            List<ProductReview> reviews = productReviewService.findAllProductReviewsByUser(user);

            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product-reviews/product/{id}")
    public ResponseEntity<List> getProductReviewByProduct(@PathVariable("id") int id) {
        try {
            Product product = productService.getProductById(id).get();
            List<ProductReview> reviews = productReviewService.findAllProductReviewsByProduct(product);

            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-product-review/product/{id}")
    public ResponseEntity<ProductReview> updateProductReview(@RequestBody ProductReview productReview,
            Authentication auth, @PathVariable("id") int id) {
        try {
            User user = userService.getUserByUsername(auth.getName());
            productReview.setUser(user);
            Optional<Product> product = productService.getProductById(id);
            if(product.isPresent()){
                productReview.setProduct(product.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            ProductReview prevProductReview = productReviewService.findProductReviewById(productReview.getId());

            if (user.getId() == prevProductReview.getUser().getId()) {
                boolean updated = productReviewService.updateProductReview(productReview);
                if (updated) {
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-product-review/product/{id}")
    public ResponseEntity<ProductReview> deleteProductReview(@RequestBody ProductReview productReview,
            Authentication auth, @PathVariable("id") int id) {
        try {
            User user = userService.getUserByUsername(auth.getName());
            productReview.setUser(user);
            Optional<Product> product = productService.getProductById(id);
            if(product.isPresent()){
                productReview.setProduct(product.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            ProductReview prevProductReview = productReviewService.findProductReviewById(productReview.getId());

            if (user.getId() == prevProductReview.getUser().getId()) {
                boolean deleted = productReviewService.deleteProductReview(productReview);
                if (deleted) {
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
