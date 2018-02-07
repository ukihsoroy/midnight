package org.ko.prototype.admin.conf.rm;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig //extends WebSecurityConfigurerAdapter
{

//    @Bean
//    public SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler() {
//        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
//        return successHandler;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.headers()
//                .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
//                .and()
//                .csrf()
//                .disable()
//                .formLogin()
//                .successHandler(authenticationSuccessHandler())
////                .failureHandler(authenticationFailureHandler())
//                .loginProcessingUrl("/login")
////                .loginPage("/index.html")
//                .permitAll()
//                .and()
//                .logout()
////                .logoutSuccessHandler(authenticationLogoutSuccessHandler())
//                .deleteCookies("JSESSIONID").invalidateHttpSession(true) // 设置退出,invalidateHttpSession设置退出后无效session
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .exceptionHandling()
////                .authenticationEntryPoint(new AjaxAwareAuthenticationEntryPoint("/index.html"))
//                .and()
//                .sessionManagement()
//                .invalidSessionUrl("/timeout")
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(false)
//                .expiredUrl("/timeout");
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
//    }
}
