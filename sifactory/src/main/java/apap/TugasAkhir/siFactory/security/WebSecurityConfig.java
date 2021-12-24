package apap.TugasAkhir.siFactory.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/api-docs").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/pegawai/add").hasAuthority("ADMIN")
                .antMatchers("/delivery/viewAll").hasAnyAuthority("STAFF_KURIR", "STAFF_OPERASIONAL")
                .antMatchers("/item/update-stok/**").hasAuthority("STAFF_GUDANG")
                .antMatchers("/item/update-stok/rui/**").hasAuthority("STAFF_GUDANG")
                .antMatchers("/item/request-update-item").hasAnyAuthority("STAFF_GUDANG",  "STAFF_OPERASIONAL")
                .antMatchers("/pegawai/view-all").hasAnyAuthority("ADMIN", "FACTORY_MANAGER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/v1/**");
        web.ignoring().antMatchers("/api-docs");
    }

    // @Bean
    // public BCryptPasswordEncoder encoder(){
    // return new BCryptPasswordEncoder();
    // }

//     @Autowired
//     public void configureGlobal(AuthenticationManagerBuilder auth) throws
//     Exception {
//     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//     auth.inMemoryAuthentication()
//     .passwordEncoder(encoder)
//     .withUser("oper1").password(encoder.encode("oper1"))
//     .roles("STAFF_OPERASIONAL");
//     }

//     @Autowired
//     public void configureGlobal(AuthenticationManagerBuilder auth) throws
//     Exception{
//     auth.inMemoryAuthentication()
//     .passwordEncoder(encoder())
//     .withUser("useradmin").password(encoder().encode("Admin!123")).roles("Admin");
//     }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    // @Autowired
    // private UserDetailsService userDetailsService;

    // @Autowired
    // public void configAuthentication(AuthenticationManagerBuilder auth) throws
    // Exception {
    // auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    // }

}
