package com.xuandung.ecommerce.model.cart

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.xuandung.ecommerce.model.user.User
import javax.persistence.*
@Entity
@Table(name = "Cart")
class Cart(
    @Id
    @GeneratedValue
    var id:Long?,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,
    @JsonManagedReference
    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL])
    var cartDetailList: Collection<CartDetail>?
)