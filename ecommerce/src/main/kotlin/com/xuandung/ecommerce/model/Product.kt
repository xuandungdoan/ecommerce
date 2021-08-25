package com.xuandung.ecommerce.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Product")
class Product(
    @Id
    @GeneratedValue
    val id: Long,
    val name: String,
    val raw_price: Long,
    val sale_price: Long,
    val full_des: String,
    val short_des: String
)