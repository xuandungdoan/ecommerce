package com.xuandung.ecommerce.controller

import com.xuandung.ecommerce.model.User
import com.xuandung.ecommerce.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController {
    @Autowired
    lateinit var userService: UserService

    @GetMapping()
    fun getAllUser(): ResponseEntity<List<User>> {
        return ResponseEntity.ok(userService.getAllUsers())
    }
}