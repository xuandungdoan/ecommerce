package com.xuandung.ecommerce.model.user


import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class RegisterReq(
    @field:NotEmpty(message = "username can not be empty")
    @field:NotNull(message = "username can not be null")
    @field:Size(min = 3, max = 15)
    val username: String,
    @field:NotEmpty(message = "password can not be empty")
    @field:NotNull(message = "password can not be null")
    @field:Pattern(regexp = "^(?=.*\\d).{4,8}$", flags = [Pattern.Flag.UNICODE_CASE])
    val password: String
)
//@Pattern(regexp = Constraints.EMAIL_REGEX)