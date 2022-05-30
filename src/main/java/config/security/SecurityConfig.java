package config.security;

import config.security.enums.RoleType;
import config.security.filter.CustomAuthFilter;
import config.security.provider.UserAuthProvider;
import config.security.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${security.ignore.url:/**}")
    private String[] ignored;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .authorizeRequests().antMatchers(ignored).permitAll().and()
                .authorizeRequests().antMatchers("/api/lms/**").hasAuthority(RoleType.ADMIN.getType())
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.csrf().disable();
//        http.cors().configurationSource();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
    }

    @Bean
    public UserAuthProvider authenticationProvider() {
        return new UserAuthProvider();
    }

    @Bean
    public CustomAuthFilter authenticationFilter() {
        return new CustomAuthFilter(ignored);
    }

    @Bean
    public JWTUtil JWTUtil(@Value("${security.jwt.key}") String textKey, @Value("${security.jwt.minutes}") int minutes) {
        return new JWTUtil(textKey, minutes);
    }
}
