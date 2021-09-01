package com.xuandung.ecommerce.service.user

import com.xuandung.ecommerce.model.user.RegisterReq
import com.xuandung.ecommerce.model.user.User
import com.xuandung.ecommerce.model.user.UserResponse

interface UserServiceI {
    fun getAllUsers(): List<User>
    fun getUserById(id: Long): UserResponse
    fun createNewUser(registerReq: RegisterReq?): UserResponse
    fun getUserByUsername(username: String): UserResponse
}