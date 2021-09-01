package com.xuandung.ecommerce.controller

import com.xuandung.ecommerce.model.cart.AddCartDetailReq
import com.xuandung.ecommerce.model.cart.Cart
import com.xuandung.ecommerce.service.cart.CartServiceI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/cart")
class CartController {
    @Autowired
    lateinit var cartService: CartServiceI

    //    @GetMapping()
//    fun getAllUser(): ResponseEntity<List<User>> {
//        return ResponseEntity.ok(userService.getAllUsers())
//    }
//
//    @GetMapping("/{id}")
//    fun getUserById(@PathVariable id: Long): ResponseEntity<UserResponse> {
//        return ResponseEntity.ok(userService.getUserById(id))
//    }
    @PostMapping("/add")
    fun getUserByUsername(
        @RequestBody addCartDetailReq: AddCartDetailReq,
        request: HttpServletRequest
    ): ResponseEntity<Cart> {
//    @RequestBody addCartDetailReq: AddCartDetailReq,
//   val addCartDetailReq = AddCartDetailReq(1,2,200,400)
        return ResponseEntity.ok(cartService.addCartDetail(addCartDetailReq, request.getAttribute("userId") as Long))
    }
//
//    @PostMapping("/register")
//    fun createNewUser(@RequestBody registerReq: RegisterReq?): ResponseEntity<UserResponse> {
//        return ResponseEntity.ok(userService.createNewUser(registerReq))
//    }
}