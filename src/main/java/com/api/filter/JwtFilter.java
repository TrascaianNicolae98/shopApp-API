package com.api.filter;

import com.api.entities.AppUser;
import com.api.error.AppException;
import com.api.service.AppUserService;
import com.api.util.JwtUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {
  @Autowired private JwtUtil jwtUtil;
  @Autowired private AppUserService userService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException, AppException {
    final String jwt = request.getHeader("authorization");
    Long userId = null;
    String path = request.getRequestURI();
    if (path.equals("/api/loginWithGoogle")) {
      filterChain.doFilter(request, response);
    } else {
      try {
        this.jwtUtil.isJwtNull(jwt);
        userId = this.jwtUtil.extractId(this.jwtUtil.extractAllClaims(jwt));
        AppUser appUser = this.userService.findById(userId);
        this.jwtUtil.validateToken(jwt, appUser);
        request.setAttribute("claims", this.jwtUtil.extractAllClaims(jwt));
        filterChain.doFilter(request, response);
      } catch (AppException e) {
        response.sendError(e.getStatusCode().value(), e.getMessage());
      }
    }
  }
}
