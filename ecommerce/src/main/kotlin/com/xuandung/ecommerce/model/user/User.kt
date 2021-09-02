package com.xuandung.ecommerce.model.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.xuandung.ecommerce.model.Role
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "User")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var username: String,
    @JsonIgnore
    var password: String,
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id")
    val role: Role
)