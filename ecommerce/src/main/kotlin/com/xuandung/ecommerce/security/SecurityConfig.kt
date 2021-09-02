package com.xuandung.ecommerce.security

import com.xuandung.ecommerce.repository.UserRepository
import com.xuandung.ecommerce.security.filter.CustomAuthenticationFilter
import com.xuandung.ecommerce.security.filter.CustomAuthorizationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    lateinit var userDetailsService: UserDetailsService

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var bCryptPasswordEncoder: PasswordEncoder
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder)
    }

    override fun configure(http: HttpSecurity): Unit {
        val customAuthFilter = CustomAuthenticationFilter(authenticationManager())
        customAuthFilter.setFilterProcessesUrl("/users/login")
        customAuthFilter.setPostOnly(true)

        http.csrf().disable()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/users/register").permitAll()
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/users").hasAnyAuthority("ROLE_USER")
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority("ROLE_USER")
        http.authorizeRequests().antMatchers("/cart/**").hasAnyAuthority("ROLE_USER")
        http.authorizeRequests().antMatchers("/order").hasAnyAuthority("ROLE_USER")
//              .antMatchers("/api/db").access("hasRole('ADMIN') or hasRole('DBA')").access("hasRole('ADMIN') or hasRole('DBA')")
        http.authorizeRequests().anyRequest().authenticated()
        http.addFilter(customAuthFilter)
        http.addFilterBefore(
            CustomAuthorizationFilter(userRepository),
            UsernamePasswordAuthenticationFilter::class.java
        )
    }


}