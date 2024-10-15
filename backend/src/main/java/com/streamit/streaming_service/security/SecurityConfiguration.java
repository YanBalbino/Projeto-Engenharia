package com.streamit.streaming_service.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {
	
	SecurityFilter securityFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	    return httpSecurity
	    		.csrf(csrf -> csrf.disable())
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .cors((cors) -> cors.configurationSource(new CorsConfigurationSource() {
	                @Override
	                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
	                    CorsConfiguration config = new CorsConfiguration();
	                    config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
	                    config.setAllowedMethods(Collections.singletonList("*"));
	                    config.setAllowCredentials(true);
	                    config.setAllowedHeaders(Collections.singletonList("*"));
	                    config.setExposedHeaders(Arrays.asList("Authorization"));
	                    config.setMaxAge(3600L);
	                    return config;
	                }
	            }))
	            .authorizeHttpRequests(authorize -> authorize
	                    .requestMatchers(HttpMethod.POST, "/api/login", "/api/users/register/**").permitAll()
	                    /*.requestMatchers(HttpMethod.PUT, "/api/profiles/update", "/api/medias/update", "/api/films/update", "/api/films/update/{id}/audio", "/api/films/update/{id}/subtitle", 
	                    		"/api/films/update/{id}/actor", "/api/actors/update", "/api/audios/update", "/api/episodes/update", "/api/episodes/update/{id}/audio", 
	                    		"/api/episodes/update/{id}/subtitle", "/api/seasons", "/api/series").hasRole("ADMIN")
	                    .requestMatchers(HttpMethod.DELETE, "/api/profiles/delete/{id}", "/api/films/delete/{id}", "/api/actors/delete/{id}", "/api/audios/delete/{id}", 
	                    		"/api/episodes/delete/{id}", "/api/medias/delete/{id}", "/api/seasons/delete/{id}", "/api/series/delete/{id}", "/api/users/delete/{id}").hasRole("ADMIN")
	                    .requestMatchers(HttpMethod.POST, "/api/films/{titulo}", "/api/medias/update", "/api/profiles/user/{idUser}", "/api/seasons/series/{seriesId}", "/api/series/{titulo}", 
                                "/api/subtitles/update", "/api/subtitles/delete/{id}").hasRole("ADMIN") 
	                    .requestMatchers(HttpMethod.GET,"/api/users/get-all").hasRole("ADMIN")
	                    .requestMatchers(HttpMethod.PUT, "api/users/update/renew", "api/users/update").hasRole("USER")
	                    .requestMatchers(HttpMethod.POST, "/api/users/solicitar-alteracao-senha", "/api/users/verificar-codigo", "/api/users/alterar-senha").hasRole("USER")
	                    .requestMatchers(HttpMethod.GET, "/api/actors/media/{mediaId}", "/api/actors/{id}", "/api/actors/name", "/api/audios/{id}", "/api/audios/films/{filmId}", 
                                "/api/audios/episodes/{episodeId}", "/api/episodes/{id}", "/api/episodes/seasons/{seasonId}", "/api/episodes/series/{seriesId}",
                                "/api/films/{id}", "/api/films/media/{mediaId}", "/api/films/genre/{profileId}", "/api/films/{profileId}",
                                "/api/medias/profiles/{profileId}", "/api/medias/actor/profiles/{profileId}", "/api/medias/title/profiles/{profileId}",
                                "/api/medias/genre/profiles/{profileId}", "/api/medias/director/profiles/{profileId}",
                                "/api/profiles/{id}", "/api/profiles/user/{idUser}", "/api/seasons/{id}", "/api/seasons", 
                                "/api/series/{id}", "/api/series/media/{mediaId}", "/api/series/genre/{profileId}", "/api/series/{profileId}", 
                                "/api/subtitles/{id}", "/api/subtitles/films/{filmId}", "/api/subtitles/episodes/{episodeId}", "/api/payments/{userId}", "/api/streaming/playlists",
                                "/api/subscriptions", "/api/users/{id}", "/api/users/max-profiles-quantity/{id}").hasRole("USER")*/
	                    .anyRequest().permitAll() // mudar para authenticate
	            )
	            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	}
	
	// h2-console, tirar depois
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
