package tacos.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tacos.entities.User;
import tacos.repositories.UserRepository;

@Configuration
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepo) {
    return username -> {
      User user = userRepo.findByUsername(username);
      if (user != null) {
        return user;
      }
      throw new UsernameNotFoundException("User ‘" + username + "’ not found");
    };
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
            .requestMatchers("/design", "/orders").hasRole("USER")
            .anyRequest().permitAll()
        )
        .formLogin(formLogin -> formLogin
            .loginPage("/login")
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/")
        )
        .csrf(csrf -> csrf
            .ignoringRequestMatchers("/h2-console/**")
        )
        .headers(headers -> headers
            .frameOptions().sameOrigin()
        );

    return http.build();
    /*
        .authorizeRequests()
        .antMatchers("/design", "/orders").access("hasRole(‘USER’)")
        .antMatchers("/", "/**").access("permitAll()")
        .and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/authenticate")
        .usernameParameter("user")
        .passwordParameter("pwd")
        .and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/design")
        .and()
        .oauth2Login()
        .loginPage("/login")
        .and()
        .logout()
        .logoutSuccessUrl("/")
        .and()
        .build();*/
  }
}