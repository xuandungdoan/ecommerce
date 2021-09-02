package com.xuandung.ecommerce.repository

import com.xuandung.ecommerce.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ProductRepository : JpaRepository<Product,Long> {
}