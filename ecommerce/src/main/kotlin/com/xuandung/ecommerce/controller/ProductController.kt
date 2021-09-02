package com.xuandung.ecommerce.controller

import com.xuandung.ecommerce.model.Product
import com.xuandung.ecommerce.service.product.ProductServiceI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/product")
class ProductController {
    @Autowired
    lateinit var productService: ProductServiceI

    @GetMapping()
    fun getProducts(@RequestParam page: Int, @RequestParam size: Int): ResponseEntity<Page<Product>> {
        return ResponseEntity.ok(productService.getProducts(page, size))
    }

    @PostMapping("/{id}")
    fun getProductDetail(
        @PathVariable id: Long
    ): ResponseEntity<Product> {
        return ResponseEntity.ok(productService.getProductDetail(id))
    }
}