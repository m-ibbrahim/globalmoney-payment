package com.globalmoney.apis.payment.config;

import com.globalmoney.apis.payment.filter.AuthenticationProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter
{
    public static final String API_SECURED_PATH_PREFIX = "/**";
    public static final String API_OPEN_PATH_PREFIX = "/open/**";

    @Autowired
    private SecurityConfigReader securityConfigReader;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                .antMatchers(API_SECURED_PATH_PREFIX).authenticated()
                .anyRequest().denyAll()
                .and()
                .addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    protected AuthenticationProcessingFilter authenticationTokenFilter()
    {
        AuthenticationProcessingFilter filter = new AuthenticationProcessingFilter(
                new AntPathRequestMatcher(API_SECURED_PATH_PREFIX), securityConfigReader);
        //filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Override
    public void configure(WebSecurity web)
    {
        web.ignoring().antMatchers(API_OPEN_PATH_PREFIX, "/", "/actuator/health", "/actuator/info", "/csrf", "/error", "/v2/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/swagger-ui.html/**", "/webjars/**", "/feature-console/**");
    }
}
