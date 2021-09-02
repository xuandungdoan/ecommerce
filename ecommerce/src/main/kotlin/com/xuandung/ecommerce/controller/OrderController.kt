package com.xuandung.ecommerce.controller

import com.xuandung.ecommerce.model.cart.Cart
import com.xuandung.ecommerce.model.order.Orders
import com.xuandung.ecommerce.service.cart.OrderServiceI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/order")
class OrderController {
    @Autowired
    lateinit var orderService: OrderServiceI

    @GetMapping()
    fun getOrdersByUsername(request: HttpServletRequest): ResponseEntity<List<Orders?>> {
        return ResponseEntity.ok(orderService.getOrderByUserId(request.getAttribute("userId") as Long))
    }

    @GetMapping("/{id}")
    fun getOrdersByUsername(@PathVariable id: Long, request: HttpServletRequest): ResponseEntity<Orders> {
        return ResponseEntity.ok(orderService.getOrderByOrderId(id, request.getAttribute("userId") as Long))
    }

    @PostMapping()
    fun createOrder(
        request: HttpServletRequest
    ): ResponseEntity<Orders> {
        return ResponseEntity.ok(orderService.createOrder(request.getAttribute("userId") as Long))
    }
}