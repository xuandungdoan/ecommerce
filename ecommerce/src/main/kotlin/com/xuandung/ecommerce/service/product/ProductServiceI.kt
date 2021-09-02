package com.xuandung.ecommerce.service.product

import com.xuandung.ecommerce.model.Product
import org.springframework.data.domain.Page

interface ProductServiceI {
    fun getProducts(page: Int, size: Int): Page<Product>
    fun getProductDetail(productId: Long): Product
}