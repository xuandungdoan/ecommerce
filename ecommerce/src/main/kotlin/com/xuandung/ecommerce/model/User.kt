package com.xuandung.ecommerce.model

import javax.persistence.*

@Entity
@Table(name = "User")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

)