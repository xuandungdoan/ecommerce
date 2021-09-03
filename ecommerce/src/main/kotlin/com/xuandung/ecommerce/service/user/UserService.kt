package com.xuandung.ecommerce.service.user

import com.xuandung.ecommerce.model.cart.Cart
import com.xuandung.ecommerce.model.user.RegisterReq
import com.xuandung.ecommerce.model.user.User
import com.xuandung.ecommerce.model.user.UserResponse
import com.xuandung.ecommerce.repository.CartRepository
import com.xuandung.ecommerce.repository.RoleRepository
import com.xuandung.ecommerce.repository.UserRepository
import com.xuandung.ecommerce.utils.Constant.Companion.ROLE_USER
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import java.util.*
import javax.validation.Valid

@Service
@Slf4j
class UserService : UserServiceI, UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var cartRepository: CartRepository
    @Autowired
    private lateinit var roleRepository: RoleRepository
    override fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    override fun getUserById(id: Long): UserResponse {
        val user = userRepository.findById(id)
        return UserResponse(user.get().username, user.get().id!!)
    }

    override fun createNewUser( registerReq: RegisterReq?): UserResponse {
        val role = roleRepository.findByName(ROLE_USER)
        val userSaved = userRepository.save(User(null, registerReq!!.username, registerReq.password, role))
        cartRepository.save(Cart(null,userSaved,null))
        return UserResponse(userSaved.username, userSaved.id!!)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
        if (user == null) {
            println("User not found in db")
            throw UsernameNotFoundException("User not found in db")
        } else {
            println("User found in db")
        }
        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            Collections.singletonList(SimpleGrantedAuthority(user.role.name))
        )

    }

    override fun getUserByUsername(username: String): UserResponse {
        val userFound = userRepository.findByUsername(username)
        return UserResponse(userFound!!.username, userFound.id!!)

    }
}