/*
package com.project.empapp.security;

import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/register").permitAll() // ログインページと登録ページは未ログインのユーザーに許可
                .anyRequest().authenticated() // それ以外のリクエストは認証が必要
                .and()
                .formLogin()
                .loginPage("/login") // ログインページの指定
                .defaultSuccessURL("/homepage", true) // ログイン成功後のデフォルトのターゲットURL
                .and()
                .logout()
                .logoutSuccessUrl("/login") // ログアウト成功後のURL
                .permitAll();
    }

    // 他のセキュリティ設定があれば追加
}

*/
