package com.xuandung.ecommerce.security.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.xuandung.ecommerce.repository.UserRepository
import com.xuandung.ecommerce.utils.Constant
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Service
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
@Service
class CustomAuthorizationFilter(var userRepository: UserRepository) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.servletPath.equals("/login")) filterChain.doFilter(request, response)
        else {
            val authorizationHeader = request.getHeader(AUTHORIZATION)
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    val token = authorizationHeader.substring("Bearer ".length)
                    val algorithm = Algorithm.HMAC256(Constant.SECRET_KEY)
                    val verifyToken = JWT.require(algorithm).build()
                    val decodeToken = verifyToken.verify(token)
                    val authorities =
                        (decodeToken.getClaim("role").asArray(String::class.java)).map {
                            SimpleGrantedAuthority(it)
                        }
                    val authentication = UsernamePasswordAuthenticationToken(decodeToken.subject, null, authorities)
                    authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authentication
                    val user = userRepository.findByUsername(decodeToken.subject)
                    request.setAttribute("username", decodeToken.subject)
                    request.setAttribute("userId", user!!.id)
                    filterChain.doFilter(request, response)
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                    val bodyResponse = mutableMapOf<String, String>()
                    bodyResponse["err_message"] = e.message!!
                    response.status = 403
                    response.contentType = MediaType.APPLICATION_JSON_VALUE
                    ObjectMapper().writeValue(response.outputStream, bodyResponse)
                }

            } else filterChain.doFilter(request, response)
        }
    }
}