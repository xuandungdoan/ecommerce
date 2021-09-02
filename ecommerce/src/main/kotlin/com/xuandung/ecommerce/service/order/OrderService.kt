package com.xuandung.ecommerce.service.cart

import com.xuandung.ecommerce.customexception.InvalidArgs
import com.xuandung.ecommerce.model.cart.Cart
import com.xuandung.ecommerce.model.order.OrderDetail
import com.xuandung.ecommerce.model.order.Orders
import com.xuandung.ecommerce.repository.CartRepository
import com.xuandung.ecommerce.repository.OrderRepository
import com.xuandung.ecommerce.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    var orderRepository: OrderRepository,
    var cartRepository: CartRepository,
    var userRepository: UserRepository
) : OrderServiceI {
    override fun getOrderByUserId(userId: Long): List<Orders?> {
        return orderRepository.findByUserId(userId)
    }

    override fun getOrderByOrderId(orderId: Long, userId: Long): Orders {
        return orderRepository.findByIdAndUserId(orderId, userId)
    }

    override fun createOrder(userId: Long): Orders {
        val user = userRepository.getById(userId)
        val cart = cartRepository.findByUserId(userId)
        if (cart.cartDetailList!!.isEmpty()) throw InvalidArgs("this cartDetailList can not be null")
        var listOrderDetail = mutableListOf<OrderDetail>()
        cart.cartDetailList!!.forEach {
            listOrderDetail.add((OrderDetail(null, it.product, it.quantity, it.price, it.amount, null)))
        }
        cartRepository.deleteById(cart.id!!)
        cartRepository.save(Cart(null, user, null))
        return orderRepository.save(Orders(null, user, listOrderDetail))
    }

}