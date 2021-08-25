package com.xuandung.ecommerce.model

import com.xuandung.ecommerce.model.user.User
import javax.persistence.*
@Entity
@Table(name = "Cart")
class Cart(
    @Id
    @GeneratedValue
    var id:Long,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User
)