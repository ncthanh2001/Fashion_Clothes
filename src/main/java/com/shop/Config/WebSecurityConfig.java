//package com.shop.Config;
//
//import com.shop.Service.Impl.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@EnableWebSecurity
//@Controller
//public class WebSecurityConfig implements WebMvcConfigurer {
//    @Autowired
//    UserService userService;
//
//
//    public PasswordEncoder passwordEncoder() {
//        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
//        return new BCryptPasswordEncoder();
//    }
//
//
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.userDetailsService(userService) // Cung cáp userservice cho spring security
//                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/", "/home").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout((logout) -> logout.permitAll());
//
//        return http.build();
//    };
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        manager.createUser(
////                User.withDefaultPasswordEncoder() // Sử dụng mã hóa password đơn giản
////                        .username("loda")
////                        .password("loda")
////                        .roles("USER") // phân quyền là người dùng.
////                        .build()
////        );
//////        UserDetails user =
//////                User.withDefaultPasswordEncoder()
//////                        .username("user")
//////                        .password("password")
//////                        .roles("USER")
//////                        .build();
//////
//////        return new InMemoryUserDetailsManager(user);
////        return manager;
////    };
//}
