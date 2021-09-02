package com.xuandung.ecommerce.repository

import com.xuandung.ecommerce.model.cart.Cart
import com.xuandung.ecommerce.model.cart.CartDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartDetailRepository : JpaRepository<CartDetail,Long> {
   fun findByProductIdAndCartId(productId:Long,cartId:Long): CartDetail?
}