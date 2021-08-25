package com.xuandung.ecommerce.model

import javax.persistence.*

@Entity
@Table(name = "CartDetail")
class CartDetail(
    @Id
    @GeneratedValue
    val id: Long,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val products:Product,
    val quantity: Int,
    val price: Long,
    val amount:Long,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    val cart: Cart
)