package com.xuandung.ecommerce.model.user

import com.xuandung.ecommerce.model.Role
import javax.persistence.*

@Entity
@Table(name = "User")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var username: String,
    var password: String,
    @ManyToOne
    @JoinColumn(name = "role_id")
    val role: Role
)