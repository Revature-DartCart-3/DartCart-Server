package com.revature.controllers;

import com.revature.models.AccountType;
import com.revature.models.User;
import com.revature.services.UserService;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController {
  @Autowired
  UserService userService;

  @PostMapping(
    value = "/register",
    consumes = "application/json",
    produces = "application/json"
  )
  public ResponseEntity<User> newUser(@RequestBody User u, @RequestParam AccountType accountType) {
    if (u.getUsername() != null) u.setUsername(
      u.getUsername().toLowerCase(Locale.ROOT)
    );
    try {
      User created = userService.addUser(u);
      if (created.getId() != 0) {
        return new ResponseEntity<>(created, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } catch (DataIntegrityViolationException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

}
