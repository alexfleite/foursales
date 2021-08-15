package br.com.foursales.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()
		.antMatchers("/home").permitAll()
		
		.antMatchers(HttpMethod.POST, "/login").permitAll().anyRequest().authenticated().and()

		// filtra requisições de login
		.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
				UsernamePasswordAuthenticationFilter.class)
		// filtra outras requisições para verificar a presença do JWT no header
		.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/configuration/security",
                                   "/swagger-ui.html",
                                   "/candidatos/delete/*",		// Criar camada de segurança sobre todas as rotas do sistema e deixar somente a rota para deletar um candidato pública.
                                   "/webjars/**");
	    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Utilizando um usuário e senha padrão para testes
		auth.inMemoryAuthentication().withUser("candidato@foursales.com.br").password("{noop}foursales").roles("ADMIN");
		
//		{
//		    "usuario": "candidato@foursales.com.br",
//		    "senha": "foursales"
//		}

	}
}
