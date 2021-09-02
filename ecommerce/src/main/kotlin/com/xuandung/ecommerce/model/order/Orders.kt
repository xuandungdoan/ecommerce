package com.xuandung.ecommerce.model.order

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.xuandung.ecommerce.model.user.User
import javax.persistence.*
@Entity
@Table(name="Orders")
class Orders(
    @Id
    @GeneratedValue
    var id:Long?,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,
    @JsonManagedReference
    @OneToMany(mappedBy = "orders", cascade = [CascadeType.ALL])
    var orderDetailList: Collection<OrderDetail>?
)