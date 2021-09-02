package com.xuandung.ecommerce.model.cart

class AddCartDetailReq(
    val productId: Long, val quantity: Int, val price: Long, val add:Boolean
)