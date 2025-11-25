package com.skillnest.m6.sistemacapacitaciones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                // Recursos estáticos y H2 sin login
                .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "/h2-console/**")
                    .permitAll()
                // Página de inicio de sesión accesible sin autenticación
                .requestMatchers("/login").permitAll()
                // Rutas de administrador
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // Rutas de empleado
                .requestMatchers("/empleado/**").hasRole("EMPLEADO")
                // API REST para ambos roles
                .requestMatchers("/api/**").hasAnyRole("ADMIN", "EMPLEADO")
                // Cualquier otra ruta requiere estar autenticado
                .anyRequest().authenticated()
            )
            // Login personalizado en /login usando la vista login.html (en español)
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)  // después de iniciar sesión va al home
                .permitAll()
            )
            // Logout en español: redirige a /login?logout
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            // Soporte para Basic Auth (API / Postman)
            .httpBasic(Customizer.withDefaults());

        // Desactivar CSRF para H2 y API
        http.csrf(csrf -> csrf
            .ignoringRequestMatchers("/h2-console/**", "/api/**")
        );

        // Permitir iframes desde el mismo origen (para H2 console)
        http.headers(headers -> headers
            .frameOptions(frame -> frame.sameOrigin())
        );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        UserDetails admin = User.withUsername("admin")
            .password(encoder.encode("admin123"))
            .roles("ADMIN")
            .build();

        UserDetails empleado = User.withUsername("empleado")
            .password(encoder.encode("empleado123"))
            .roles("EMPLEADO")
            .build();

        return new InMemoryUserDetailsManager(admin, empleado);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
