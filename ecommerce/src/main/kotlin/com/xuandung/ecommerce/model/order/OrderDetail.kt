package com.xuandung.ecommerce.model.order

import com.fasterxml.jackson.annotation.JsonBackReference
import com.xuandung.ecommerce.model.Product
import javax.persistence.*

@Entity
@Table(name = "OrderDetail")
class OrderDetail(
    @Id
    @GeneratedValue
    @Column(name="id")
    val id: Long?,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,
    @Column(name="quantity")
    var quantity: Int,
    @Column(name="price")
    val price: Long,
    @Column(name="amount")
    var amount: Long,
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    val orders: Orders?
)