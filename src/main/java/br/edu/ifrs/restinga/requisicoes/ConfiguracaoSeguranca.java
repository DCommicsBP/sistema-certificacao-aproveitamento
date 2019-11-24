/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.requisicoes;


import br.edu.ifrs.restinga.requisicoes.autenticacao.FiltroPorToken;
//import br.edu.ifrs.restinga.requisicoes.autenticacao.MeuUserDetailsService;
import br.edu.ifrs.restinga.requisicoes.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {

    @Autowired
    UsuarioDAO usuarioDAO;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //o GET login pode ser acessado sem autenticação 
                .antMatchers(HttpMethod.POST, "/api/usuarios/login/").permitAll()
                // Caso o sistema permita o autocadastro                
                .antMatchers(HttpMethod.POST, "/api/usuarios/").permitAll()
                .antMatchers(HttpMethod.POST, "/api/usuarios/aluno/").permitAll()
                // permite o acesso somente se autenticado
                .antMatchers("/api/**").authenticated()
                .and().httpBasic()
                 .and().
                addFilterBefore(new  FiltroPorToken(usuarioDAO)
                        , BasicAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();
    }

}