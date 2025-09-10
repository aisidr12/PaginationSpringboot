package com.paginationpractice.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/****
 * this is a security config Class which is responsible for giving access to the resource
 * that will create the token, usually is the controller with the direction /api/authenticate
 * in order to create a new token to access to the resources
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtRequestFilter jwtRequestFilter;

  public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
    this.jwtRequestFilter = jwtRequestFilter;
  }

  /**
   * Here we modify the filter in which we disable first the crsf, after that we authorizeHttpRequests
   * with the endpoint that we want to allow to access in order to authenticate, that is why we permitAll any request
   * that is already authenticated. Then, we manage as session with the state STATELESS.
   * Finally, we explicit add the filter before with we already created and then build
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth ->
            auth.requestMatchers("/api/authenticate")
                .permitAll().anyRequest().authenticated())
        .sessionManagement(session -> session.sessionCreationPolicy(
            SessionCreationPolicy.STATELESS));
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}