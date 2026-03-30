package com.springboot2.book.config.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/h2-console/**", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/api/v1/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                );

        return http.build();
    }
}

/*
* 왜 못 쓰게 됐나?
WebSecurityConfigurerAdapter 방식은 상속 기반 설정인데,
Spring Security 최신 버전으로 오면서 컴포넌트(빈) 기반 설정으로 완전히 전환됐어요.
즉, 예전처럼:
extends WebSecurityConfigurerAdapter
configure(HttpSecurity http) 오버라이드
하는 방식이 deprecated → 제거된 거예요.

6.x에서의 표준 방식
이제는 SecurityFilterChain을 @Bean으로 등록해서 설정합니다.
예전:
authorizeRequests()
antMatchers()
지금:
authorizeHttpRequests()
requestMatchers()
이렇게 API도 같이 바뀌었어요.

왜 이렇게 바꿨을까? (의도)
상속보다 명시적인 Bean 구성이 더 유연함
여러 보안 체인(멀티 체인) 구성 쉬움
자동설정/커스터마이징 충돌 감소
테스트/재사용성 개선

네 코드 기준으로 실무 포인트
네가 겪은 문제처럼, 새 방식에서는 꼭:
설정 클래스가 스프링 빈이어야 함 (@Configuration)
SecurityFilterChain 메서드가 빈이어야 함 (@Bean)
둘 중 하나라도 빠지면 “설정 작성했는데 안 먹는” 상황이 바로 나옵니다.

원하면 내가 **“구버전 코드 ↔ 신버전 코드 1:1 대응표”**로 딱 외우기 쉽게 정리해줄게요.
* */
//// spring security 6.1+ 이후 WebSecurityConfigurerAdapter가 removel됨
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final CustomOAuth2UserService customOAuth2UserService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .headers().frameOptions().disable()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
//                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
//                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .and()
//                .oauth2Login()
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService);
//    }
//}