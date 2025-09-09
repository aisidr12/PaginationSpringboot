package com.paginationpractice.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *  We have to extend to oncePerRequestFilter in order to check everytime
 *  that a request is sent. After that we have to implement doFilterInternal in which we have to
 *  make sure that in the header contains "Authorization" and begins with Bearer.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private final JwtTokenUtil jwtTokenUtil;

  public JwtRequestFilter(JwtTokenUtil jwtTokenUtil) {
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    final String authorizationHeader = request.getHeader("Authorization");

    String username = null;
    String jwt = null;

    //Verify header contains contains Bearer and extract the username
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      jwt = authorizationHeader.substring(7);
      username = jwtTokenUtil.extractUsername(jwt);
    }

    //Check if the username is ok and that the request is not authenticated yet. this avoids that the filter
    // auth the same request twice.
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = new User(username, "", new ArrayList<>());
      if (jwtTokenUtil.validateToken(jwt)) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
        /*
         Finalmente, se crea un UsernamePasswordAuthenticationToken con el UserDetails y
         se establece en el contexto de seguridad de Spring a través de
         SecurityContextHolder.getContext().setAuthentication(...).
         Esto le dice a Spring Security: "Esta petición está autenticada y pertenece a este usuario".
         */
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }
    filterChain.doFilter(request,response);
  }
}
