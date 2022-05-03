package com.app.cargarage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userServiceImpl")
    @Autowired
    UserDetailsService userDetailsService;

    private static final String[] AUTH_WHITELIST = {

            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"

    };

    /**
     * Deze methode wordt gebruikt wanneer de gebruiker inlogt .
     * Het zal de gebruiker authenticeren.
     *
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    /**
     * Deze methode autoriseert welke verzoeken moeten worden geautoriseerd en welke niet.
     * Dit regelt ook welke gebruiker toegang heeft tot welke paden en welke gebruiker welke kan doen.
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/appointments/**").hasAnyRole("MECHANIC", "ADMINISTRATIVE","ADMIN")
                .antMatchers("/car/addRepairingActionsInCar/**", "/car/installPartsInCar/**").hasAnyRole("MECHANIC","ADMIN")
                .antMatchers("/car/list", "/car/list/repairedCars/**", "/car/list/unRepairedCars/**").hasAnyRole("CASHIER", "MECHANIC", "ADMINISTRATIVE", "ADMIN")
                .antMatchers("/car/changeStatusToRepaired/**").hasAnyRole("MECHANIC", "ADMINISTRATIVE", "ADMIN")
                .antMatchers("/car/**").hasAnyRole("ADMINISTRATIVE", "ADMIN")
                .antMatchers("/customer/list").hasAnyRole("MECHANIC", "ADMINISTRATIVE", "ADMIN")
                .antMatchers("/customer/**").hasAnyRole("ADMINISTRATIVE", "ADMIN")
                .antMatchers("/receipts/**").hasAnyRole("CASHIER", "ADMIN")
                .antMatchers("/repairOperations/**").hasAnyRole("BACKOFFICE", "MECHANIC","ADMIN")
                .antMatchers("/parts/**").hasAnyRole("BACKOFFICE", "MECHANIC","ADMIN")
                .antMatchers("/repairSchedule/**").hasAnyRole("ADMINISTRATIVE", "ADMIN")
                .antMatchers("/vouchers/**").hasAnyRole("MECHANIC", "CASHIER","ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    /**
     * Dit is de bean van Bcrypt die we in onze gebruikersservice hebben gebruikt om het wachtwoord te versleutelen tijdens het registreren.
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Het is voor het toestaan van deze 2 paden dat we html-pagina's hebben.
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
