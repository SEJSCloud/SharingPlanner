package com.sharingplanner.config.security;

import com.sharingplanner.config.security.checker.AuthChecker;
import com.sharingplanner.config.security.enums.RoleType;
import com.sharingplanner.config.security.filter.CustomAuthFilter;
import com.sharingplanner.config.security.handler.LoginFailureHandler;
import com.sharingplanner.config.security.handler.LoginSuccessHandler;
import com.sharingplanner.config.security.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${security.ignore.url}")
    private String[] ignored;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private AuthChecker authChecker;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("userId")
                    .passwordParameter("userPw")
                    .defaultSuccessUrl("/index")
                    .successHandler(loginSuccessHandler)
                    .failureHandler(loginFailureHandler)
                .and()
                    .authorizeRequests().antMatchers(ignored).permitAll()
                .and()
                    .authorizeRequests().antMatchers("/admin/**").hasAuthority(RoleType.ADMIN.getType())
                    .anyRequest().authenticated()
                .and()
                    .addFilterBefore(new CustomAuthFilter(authChecker, ignored), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
//        http.cors().configurationSource();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTUtil JWTUtil(@Value("${security.jwt.key}") String textKey, @Value("${security.jwt.minutes}") int minutes) {
        return new JWTUtil(textKey, minutes);
    }
}
