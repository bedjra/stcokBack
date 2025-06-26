//package com.Megatram.Megatram.config;
//
//import com.Megatram.Megatram.service.UtilisateurService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .anyRequest().permitAll()
//                )
//                .csrf(csrf -> csrf.disable());
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public JwtFilter jwtFilter(UtilisateurService utilisateurService, JwtUtil jwtUtil) {
//        return new JwtFilter(utilisateurService, jwtUtil);
//    }
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//}
//
//
//

