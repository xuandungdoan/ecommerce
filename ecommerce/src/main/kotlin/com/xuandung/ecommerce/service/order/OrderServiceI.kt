package com.xuandung.ecommerce.service.cart

import com.xuandung.ecommerce.model.cart.Cart
import com.xuandung.ecommerce.model.order.Orders

interface OrderServiceI {
    fun getOrderByUserId(userId: Long): List<Orders?>
    fun getOrderByOrderId(orderId: Long, userId: Long): Orders
    fun createOrder(userId: Long): Orders
}