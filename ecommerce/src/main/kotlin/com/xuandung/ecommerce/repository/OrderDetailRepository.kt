package com.xuandung.ecommerce.repository

import com.xuandung.ecommerce.model.cart.Cart
import com.xuandung.ecommerce.model.cart.CartDetail
import com.xuandung.ecommerce.model.order.OrderDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderDetailRepository : JpaRepository<OrderDetail,Long> {
   fun findByProductIdAndOrdersId(productId:Long,ordersId:Long): OrderDetail?
}