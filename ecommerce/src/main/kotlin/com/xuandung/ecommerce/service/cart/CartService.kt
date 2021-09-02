package com.xuandung.ecommerce.service.cart

import com.xuandung.ecommerce.customexception.InvalidArgs
import com.xuandung.ecommerce.model.cart.AddCartDetailReq
import com.xuandung.ecommerce.model.cart.Cart
import com.xuandung.ecommerce.model.cart.CartDetail
import com.xuandung.ecommerce.repository.CartDetailRepository
import com.xuandung.ecommerce.repository.CartRepository
import com.xuandung.ecommerce.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class CartService(
    var cartRepository: CartRepository,
    var productRepository: ProductRepository,
    var cartDetailRepository: CartDetailRepository
) : CartServiceI {
    override fun getCartByUserId(userId: Long): Cart {
        return cartRepository.findByUserId(userId)
    }

    override fun addCartDetail(addCartDetailReq: AddCartDetailReq, userId: Long): Cart {
        var cart = cartRepository.findByUserId(userId)
        val product = productRepository.findById(addCartDetailReq.productId)
        val cartDetail = CartDetail(
            null,
            product.get(),
            addCartDetailReq.quantity,
            addCartDetailReq.price,
            addCartDetailReq.quantity * addCartDetailReq.price,
            cart
        )
        if (cart.cartDetailList != null) {
            var cartDetailFound = cartDetailRepository.findByProductIdAndCartId(addCartDetailReq.productId, cart.id!!)
            if (cartDetailFound != null) {
                if (addCartDetailReq.add) cartDetailFound.quantity += addCartDetailReq.quantity
                else cartDetailFound.quantity -= addCartDetailReq.quantity
                if (cartDetailFound.quantity <= 0) throw InvalidArgs("the quantity can not decrease to 0")
                cartDetailFound.amount = cartDetailFound.quantity * cartDetailFound.price
                cartDetailRepository.save(cartDetailFound)
                return cart
            } else {
                val cartTemp = cart.cartDetailList!!.toMutableList()
                cartTemp.add(cartDetail)
                cart.cartDetailList = cartTemp
            }
        } else {
            cart.cartDetailList = listOf(cartDetail)
        }
        return cartRepository.saveAndFlush(cart)
    }

    override fun deleteCartDetail(cartDetailId: Long) {
        cartDetailRepository.deleteById(cartDetailId)
    }
}