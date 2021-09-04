package com.xuandung.ecommerce.utils

import com.xuandung.ecommerce.model.Product
import com.xuandung.ecommerce.model.Role
import com.xuandung.ecommerce.model.cart.Cart
import com.xuandung.ecommerce.model.user.User
import com.xuandung.ecommerce.repository.CartRepository
import com.xuandung.ecommerce.repository.ProductRepository
import com.xuandung.ecommerce.repository.RoleRepository
import com.xuandung.ecommerce.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class InitData(
    var roleRepository: RoleRepository,
    var userRepository: UserRepository,
    var productRepository: ProductRepository,
    var cartRepository: CartRepository
) {

    @PostConstruct
    fun initDate() {

        roleRepository.saveAll(listOf(Role(null, Constant.ROLE_USER), Role(null, Constant.ROLE_ADMIN)))
        val user = userRepository.save(User(null, "a", passwordEncoder().encode("a"), Role(1, Constant.ROLE_USER)))
        productRepository.save(Product(null, "product_sample", 10, 9, "full des", "short des"))
        productRepository.save(Product(null, "product_sample2", 10, 9, "full des", "short des"))
        productRepository.save(Product(null, "product_sample", 10, 9, "full des", "short des"))
        productRepository.save(Product(null, "product_sample2", 10, 9, "full des", "short des"))
        productRepository.save(Product(null, "product_sample", 10, 9, "full des", "short des"))
        productRepository.save(Product(null, "product_sample2", 10, 9, "full des", "short des"))
        productRepository.save(Product(null, "product_sample", 10, 9, "full des", "short des"))
        productRepository.save(Product(null, "product_sample2", 10, 9, "full des", "short des"))
        productRepository.save(Product(null, "product_sample", 10, 9, "full des", "short des"))
        productRepository.save(Product(null, "product_sample2", 10, 9, "full des", "short des"))
        productRepository.save(Product(null, "product_sample", 10, 9, "full des", "short des"))
        productRepository.save(Product(null, "product_sample2", 10, 9, "full des", "short des"))
        productRepository.save(Product(null, "product_sample", 10, 9, "full des", "short des"))
        productRepository.save(Product(null, "product_sample2", 10, 9, "full des", "short des"))
        cartRepository.save(Cart(null, user, null))
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
