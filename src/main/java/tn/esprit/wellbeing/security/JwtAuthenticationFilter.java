package tn.esprit.wellbeing.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {


    @Autowired
    private UserService userService;

    private final TokenProvider jwtTokenUtil;

    public JwtAuthenticationFilter(TokenProvider jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authorization != null && authorization.startsWith(Constant.General.BEARER)) {
            token = authorization.replace(Constant.General.BEARER, "");

            try {
                username = jwtTokenUtil.getUsername(token);
            } catch (IllegalArgumentException e) {
                log.error("An error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                log.error("Token is expired", e);
            } catch (SignatureException e) {
                log.error("Authentication Failed. Username or Password not valid.");
            }
        }

        if (username != null && authentication == null) {
            User user = (User) userService.loadUserByUsername(username);

            if (jwtTokenUtil.isTokenValid(token, user)) {
                Authentication authenticationToken = jwtTokenUtil.getAuthenticationToken(token, authentication, user);

                log.debug("Authenticated user: {}, setting security context", username);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        chain.doFilter(request, response);
    }

}
