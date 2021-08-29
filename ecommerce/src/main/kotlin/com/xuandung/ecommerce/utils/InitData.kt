package com.xuandung.ecommerce.utils

import com.xuandung.ecommerce.model.Role
import com.xuandung.ecommerce.model.user.User
import com.xuandung.ecommerce.repository.RoleRepository
import com.xuandung.ecommerce.repository.UserRepository
import com.xuandung.ecommerce.utils.Constant.Companion.ROLE_ADMIN
import com.xuandung.ecommerce.utils.Constant.Companion.ROLE_USER
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class InitData(var roleRepository: RoleRepository, var userRepository: UserRepository) {

    @PostConstruct
    fun initDate() {

        roleRepository.saveAll(listOf(Role(null, Constant.ROLE_USER), Role(null, Constant.ROLE_ADMIN)))
        userRepository.save(User(null,"a",passwordEncoder().encode("a"),Role(1,Constant.ROLE_USER)))
    }
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
