package com.xuandung.ecommerce.security.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.xuandung.ecommerce.utils.Constant.Companion.SECRET_KEY
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import java.util.concurrent.TimeUnit
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationFilter(authenticationManager: AuthenticationManager) :
    UsernamePasswordAuthenticationFilter() {
    private val authManager: AuthenticationManager = authenticationManager

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val authenticationToken =
            UsernamePasswordAuthenticationToken(request.getParameter("username"), request.getParameter("password"))
        return authManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val user = authResult.principal as User
        val algorithm = Algorithm.HMAC256(SECRET_KEY)
        val roles = user.authorities.map { it.authority }
        val accessToken = JWT.create()
            .withSubject(request.getParameter(user.username))
            .withExpiresAt(Date(System.currentTimeMillis() + 10 * 60 * 1000))
            .withIssuer(request.requestURI.toString())
            .withClaim("role", roles)
            .sign(algorithm)
        val refreshToken = JWT.create()
            .withSubject(request.getParameter(user.username))
            .withExpiresAt(Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7)))
            .withIssuer(request.requestURI.toString())
            .withClaim("role", roles)
            .sign(algorithm)

        val bodyResponse = mutableMapOf<String, String>()
        bodyResponse["access_token"] = accessToken
        bodyResponse["refresh_token"] = refreshToken
        response.contentType = APPLICATION_JSON_VALUE
        ObjectMapper().writeValue(response.outputStream, bodyResponse)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException
    ) {
        val bodyResponse = mutableMapOf<String, String>()
        bodyResponse["err_message"] = failed.message!!
        response.status = 403
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        ObjectMapper().writeValue(response.outputStream, bodyResponse)

    }
}