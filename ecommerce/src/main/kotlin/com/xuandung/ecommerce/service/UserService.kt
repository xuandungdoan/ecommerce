package com.xuandung.ecommerce.service

import com.xuandung.ecommerce.model.User
import com.xuandung.ecommerce.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    private lateinit var userRepository: UserRepository
    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }
}