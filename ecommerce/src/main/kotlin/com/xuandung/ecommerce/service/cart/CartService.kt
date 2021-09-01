package com.xuandung.ecommerce.service.cart

import com.xuandung.ecommerce.model.cart.AddCartDetailReq
import com.xuandung.ecommerce.model.cart.Cart
import com.xuandung.ecommerce.model.cart.CartDetail
import com.xuandung.ecommerce.repository.CartRepository
import com.xuandung.ecommerce.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class CartService(var cartRepository: CartRepository,var productRepository: ProductRepository):CartServiceI {
    override fun getCartByUserId(userId: Long): Cart {
       return cartRepository.findByUserId(userId)
    }

    override fun addCartDetail(addCartDetailReq: AddCartDetailReq, userId:Long): Cart {
        var cart = cartRepository.findByUserId(userId)
        val product = productRepository.findById(addCartDetailReq.productId)
        val cartDetail = CartDetail(null,product.get(),addCartDetailReq.quantity,addCartDetailReq.price,addCartDetailReq.amount,cart)
        if (cart.cartDetailList != null){
            val cartTemp = cart.cartDetailList!!.toMutableList()
            cartTemp.add(cartDetail)
            cart.cartDetailList=cartTemp
        }else{
            cart.cartDetailList = listOf(cartDetail)
        }
        return cartRepository.saveAndFlush(cart)
    }
}