package com.xuandung.ecommerce.repository

import com.xuandung.ecommerce.model.cart.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : JpaRepository<Cart,Long> {
   fun findByUserId(userId:Long): Cart
}