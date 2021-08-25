package com.xuandung.ecommerce.service

import com.xuandung.ecommerce.model.user.RegisterReq
import com.xuandung.ecommerce.model.user.User
import com.xuandung.ecommerce.model.user.UserResponse
import com.xuandung.ecommerce.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService : UserServiceI {
    @Autowired
    private lateinit var userRepository: UserRepository
    override fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    override fun getUserById(id: Long): UserResponse {
        val user = userRepository.findById(id)
        return UserResponse(user.get().username, user.get().id!!)
    }

    override fun createNewUser(registerReq: RegisterReq?): UserResponse {
        val userSaved = userRepository.saveAndFlush(User(null, registerReq!!.username, registerReq!!.password))
        return UserResponse(userSaved.username, userSaved.id!!)
    }
}