package com.xuandung.ecommerce.service.cart

import com.xuandung.ecommerce.model.cart.AddCartDetailReq
import com.xuandung.ecommerce.model.cart.Cart
import com.xuandung.ecommerce.model.cart.CartDetail

interface CartServiceI {
    fun getCartByUserId(userId:Long): Cart
    fun addCartDetail(addCartDetailReq: AddCartDetailReq, userId:Long): Cart
}