package com.xuandung.ecommerce.repository

import com.xuandung.ecommerce.model.order.Orders
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Orders, Long> {
    fun findByUserId(userId: Long): List<Orders>
    fun findByIdAndUserId(id: Long, userId: Long): Orders
}