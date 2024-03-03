package users.userservice.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SpringSecurity {
//    @Bean
//    public SecurityFilterChain filteringCriteria(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.cors().disable();
//        httpSecurity.csrf().disable();
//
//
//        httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
//        return httpSecurity.build();
//    }
    @Bean
    public BCryptPasswordEncoder cryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
