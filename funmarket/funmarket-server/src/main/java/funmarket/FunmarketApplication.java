package funmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;

@SpringBootApplication
public class FunmarketApplication {
    public static void main(String[] args) {
        SpringApplication.run(FunmarketApplication.class, args);
    }
}

@Configuration
class WebSecurityConfig {

    /*
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new AuthenticationManagerBuilder(new NopPostProcessor())
            .inMemoryAuthentication().withUser("user").password("password").roles("USER")
            .and().and().build();
    }
    */

    private static class NopPostProcessor implements ObjectPostProcessor {
        @Override
        @SuppressWarnings("unchecked")
        public Object postProcess(Object object) {
            return object;
        }
    };
}