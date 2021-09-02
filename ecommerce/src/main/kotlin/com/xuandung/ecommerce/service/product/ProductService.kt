package com.xuandung.ecommerce.service.product

import com.xuandung.ecommerce.model.Product
import com.xuandung.ecommerce.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ProductService : ProductServiceI {
    @Autowired
    lateinit var productRepository: ProductRepository
    override fun getProducts(page: Int, size: Int): Page<Product> {
        return productRepository.findAll(PageRequest.of(page, size))
    }

    override fun getProductDetail(productId: Long): Product {
        return productRepository.getById(productId)
    }
}