package com.xuandung.ecommerce.security

import com.xuandung.ecommerce.repository.UserRepository
import com.xuandung.ecommerce.security.filter.CustomAuthenticationFilter
import com.xuandung.ecommerce.security.filter.CustomAuthorizationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
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
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


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
        http.csrf().disable()
        http.cors()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/user/register").permitAll()
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/product/**").permitAll()
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/product").permitAll()
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/user").hasAnyAuthority("ROLE_USER")
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/user/**").hasAnyAuthority("ROLE_USER")
        http.authorizeRequests().antMatchers("/cart/**").hasAnyAuthority("ROLE_USER")
        http.authorizeRequests().antMatchers("/order").hasAnyAuthority("ROLE_USER")
//              .antMatchers("/api/db").access("hasRole('ADMIN') or hasRole('DBA')").access("hasRole('ADMIN') or hasRole('DBA')")
        http.authorizeRequests().anyRequest().authenticated()
        http.addFilter(CustomAuthenticationFilter(authenticationManager()))
        http.addFilterBefore(
            CustomAuthorizationFilter(userRepository),
            UsernamePasswordAuthenticationFilter::class.java
        )
    }

//    @Bean
//    fun corsFilter(): CorsFilter {
//        val source = UrlBasedCorsConfigurationSource()
//        val config = CorsConfiguration()
//        config.allowCredentials = true
//        config.addAllowedOrigin("*")
//        config.addAllowedHeader("*")
//        config.addAllowedMethod("OPTIONS")
//        config.addAllowedMethod("GET")
//        config.addAllowedMethod("POST")
//        config.addAllowedMethod("PUT")
//        config.addAllowedMethod("DELETE")
//        source.registerCorsConfiguration("/**", config)
//        return CorsFilter(source)
//    }

    @Bean
    fun cors(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowedMethods("HEAD","GET","POST","PUT","DELETE","PATCH").allowedHeaders("*");
            }
        }
    }
}