package org.example.springsecurity.springsecurity.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SpringConfig {

    @Autowired
    private SuccessHandler successHandler;


    @Bean
//Encode Passwords
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}
    @Autowired
private EmployeeLoadData employeeLoadData;


/*
@Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin = User.builder()
                .username("haroon")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN")
                .build();


        UserDetails user = User.builder()
                .username("ahmad")
                .password(passwordEncoder().encode("345"))
                .roles("USER")
                .build();

return new InMemoryUserDetailsManager(admin,user);
    }*/
@Bean
    public UserDetailsService userDetailsServices(){
       return employeeLoadData;
    }
@Bean
    public AuthenticationProvider authenticationProvider(){


        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(employeeLoadData);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;

    }




@Bean
    public SecurityFilterChain filterChain(HttpSecurity secure) throws Exception {


    return secure
             .csrf(csrf->csrf.disable())
    .authorizeHttpRequests(auth->auth
             .requestMatchers("/home","/register/**").permitAll() //home can access any
             .requestMatchers("/admin/**").hasRole("ADMIN")
            // admin can access
             .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")

             .anyRequest().authenticated()

     ).formLogin(form-> form.
                     loginPage("/login").
                     successHandler(successHandler).
                     permitAll()).
    exceptionHandling(exception -> exception
            .accessDeniedPage("/access-denied")  // 👈 this handles 403
    ).build();

    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

}
