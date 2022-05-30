package config.security.filter;

import config.security.checker.AuthChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class CustomAuthFilter extends OncePerRequestFilter {
    public CustomAuthFilter(String[] ignoreUrls) {
        this.ignoreUrls = Arrays.asList(ignoreUrls.clone());
    }
    @Autowired
    private AuthChecker authChecker;
    private final List<String> ignoreUrls;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (shouldNotFilter(request) || authChecker.isAuthenticated(request)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return ignoreUrls.stream().anyMatch(ignoreUrl -> pathMatcher.match(ignoreUrl, request.getServletPath()));
    }
}
