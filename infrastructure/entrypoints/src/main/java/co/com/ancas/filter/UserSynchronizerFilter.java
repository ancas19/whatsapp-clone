package co.com.ancas.filter;

import co.com.ancas.services.UserAppService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UserSynchronizerFilter extends OncePerRequestFilter {
    private final UserAppService userAppService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isIntance=!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
        if(isIntance){
            JwtAuthenticationToken token=(JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            userAppService.synchronizeUser(token.getToken());
        }
        filterChain.doFilter(request, response);
    }
}
