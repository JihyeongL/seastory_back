package com.jl.seastory.configuration;

import com.jl.seastory.jwt.JwtAccessDeniedHandler;
import com.jl.seastory.jwt.JwtAuthenticationEntryPoint;
import com.jl.seastory.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(TokenProvider tokenProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler){
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception{

        http.csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/api/v1/members/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/v1/licenses-management/**").hasRole("ADMIN")
                .antMatchers("/api/v1/licenses/**").permitAll()
                .and()
                .cors()
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
        return http.build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        // 로컬 React에서 오는 요청은 CORS 허용해준다.
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3001" ));// 해당 ip만 응답
//        configuration.setAllowedOrigins(Arrays.asList("http://192.168.0.161:3000" ));// 해당 ip만 응답
        //configuration.setAllowedOrigins(Arrays.asList("http://43.200.33.166:3000" ));// 해당 ip만 응답

        configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE"));// 해당메소드만응답하겠다
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Content-Type", "Access-Control-Allow-Headers", "Authorization", "X-Requested-With"));// 해당 헤더의 응답만허용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
