package com.xuandung.ecommerce.model.user

//data class Foo(@get:Pattern(regexp = "^\\d+\$", message = "...") val houseNr: String)) //getter
//data class Foo(@field:Pattern(regexp = "^\\d+\$", message = "...") val houseNr: String)) //field
data class RegisterReq( val username: String, val password: String)