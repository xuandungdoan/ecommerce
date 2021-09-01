package com.xuandung.ecommerce.controller

import com.xuandung.ecommerce.model.user.RegisterReq
import com.xuandung.ecommerce.model.user.User
import com.xuandung.ecommerce.model.user.UserResponse
import com.xuandung.ecommerce.service.user.UserServiceI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


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
    @GetMapping("/username")
    fun getUserByUsername(request: HttpServletRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.getUserByUsername(request.getAttribute("username") as String))
    }

    @PostMapping("/register")
    fun createNewUser(@RequestBody registerReq: RegisterReq?): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.createNewUser(registerReq))
    }
}