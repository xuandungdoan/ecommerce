package com.xuandung.ecommerce.controller

import com.xuandung.ecommerce.model.user.RegisterReq
import com.xuandung.ecommerce.model.user.User
import com.xuandung.ecommerce.model.user.UserResponse
import com.xuandung.ecommerce.service.UserService
import com.xuandung.ecommerce.service.UserServiceI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users")
class UserController {
    @Autowired
    lateinit var userService: UserServiceI

    @GetMapping()
    fun getAllUser(): ResponseEntity<List<User>> {
        return ResponseEntity.ok(userService.getAllUsers())
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.getUserById(id))
    }

    @PostMapping("/register")
    fun createNewUser(@RequestBody registerReq: RegisterReq?): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.createNewUser(registerReq))
    }
}