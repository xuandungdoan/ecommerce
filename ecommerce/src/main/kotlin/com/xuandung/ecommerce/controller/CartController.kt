package com.xuandung.ecommerce.controller

import com.xuandung.ecommerce.model.cart.AddCartDetailReq
import com.xuandung.ecommerce.model.cart.Cart
import com.xuandung.ecommerce.model.cart.DeleteCartDetailReq
import com.xuandung.ecommerce.service.cart.CartServiceI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/cart")
class CartController {
    @Autowired
    lateinit var cartService: CartServiceI

    @GetMapping()
    fun getCartByUsername(request: HttpServletRequest): ResponseEntity<Cart> {
        return ResponseEntity.ok(cartService.getCartByUserId(request.getAttribute("userId") as Long))
    }

    @PostMapping("/add")
    fun addCartDetail(
        @RequestBody addCartDetailReq: AddCartDetailReq,
        request: HttpServletRequest
    ): ResponseEntity<Cart> {
        return ResponseEntity.ok(cartService.addCartDetail(addCartDetailReq, request.getAttribute("userId") as Long))
    }

    @DeleteMapping("/delete")
    fun deleteCart(
        @RequestBody deleteCartDetailReq: DeleteCartDetailReq,
        request: HttpServletRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(cartService.deleteCartDetail(deleteCartDetailReq.cartDetailId))
    }
}