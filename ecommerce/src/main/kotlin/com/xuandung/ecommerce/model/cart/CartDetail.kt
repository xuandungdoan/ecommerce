package com.xuandung.ecommerce.model.cart

import com.fasterxml.jackson.annotation.JsonBackReference
import com.xuandung.ecommerce.model.Product
import javax.persistence.*

@Entity
@Table(name = "CartDetail")
class CartDetail(
    @Id
    @GeneratedValue
    val id: Long?,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,
    val quantity: Int,
    val price: Long,
    val amount:Long,
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    val cart: Cart
)