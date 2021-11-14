package sv.com.iblat.billing.backend.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.web.filter.GenericFilterBean;

import lombok.extern.slf4j.Slf4j;
import sv.com.iblat.billing.backend.core.service.impl.UserDetailsServiceImpl;
import sv.com.iblat.billing.backend.events.CustomSuccessHandler;

/**
 * Configures spring security, doing the following:
 * <li>Bypass security checks for static resources,</li>
 * <li>Restrict access to the application, allowing only logged in users,</li>
 * <li>Set up the login form,</li>
 * <li>Configures the {@link UserDetailsServiceImpl}.</li>

 */
@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	private static final String LOGIN_PROCESSING_URL = "/login";
	private static final String LOGIN_FAILURE_URL = "/login?error";
	private static final String LOGIN_URL = "/login";
	
	**/
	
	@Autowired
	private CustomSuccessHandler customSuccessHandler;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	class LoginPageFilter extends GenericFilterBean{
		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			if(SecurityUtils.isUserLoggedIn() && ((HttpServletRequest) request).getRequestURI().equals("/login") || 
						((HttpServletRequest) request).getRequestURI().equals("/registration")) {
				log.info(String.format("User: '%1s' is already authenticated and trying to access the login or registration page, redirecting...", SecurityUtils.getUsername()));
				if(SecurityUtils.isAdmin()) {
					
					((HttpServletResponse) response).sendRedirect("/admin/home");
				}else {
					((HttpServletResponse) response).sendRedirect("/");
				}
			}
			
			chain.doFilter(request, response);
			
		}
	}

	/**
	 * The password encoder to use when encrypting passwords.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	/**
	 * Registers our UserDetailsService and the password encoder to be used on login attempts.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	

	/**
	 * Require login to access internal pages and configure login form.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new LoginPageFilter(), DefaultLoginPageGeneratingFilter.class);
		
		http.authorizeRequests()
				.and()
			.headers()
				.frameOptions()
				.sameOrigin()
				.and()
			.formLogin()
				.permitAll()
				.successHandler(customSuccessHandler)
				.and()
			.logout()
				.permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/login","/registration")
				.not().authenticated()
				.and()
			.authorizeRequests()
				.antMatchers("/user/**", "/").hasAuthority("USER")
				.antMatchers("/admin/**","/api/**","/new","/edit/**","/delete/**").hasAuthority("ADMIN")
				.anyRequest().authenticated();
			
	
		
		// Not using Spring CSRF here to be able to use plain HTML for the login page
		
		/**
		http.csrf().disable()

				// Register our CustomRequestCache, that saves unauthorized access attempts, so
				// the user is redirected after login.
				.requestCache()

				// Restrict access to our application.
				.and().authorizeRequests()

				// Allow all flow internal requests.
				.requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()

				// Allow all requests by logged in users.
				.anyRequest().hasAnyAuthority("")

				// Configure the login page.
				.and().formLogin().loginPage(LOGIN_URL).permitAll().loginProcessingUrl(LOGIN_PROCESSING_URL)
				.failureUrl(LOGIN_FAILURE_URL)

				// Register the success handler that redirects users to the page they last tried
				// to access
				.successHandler(new SavedRequestAwareAuthenticationSuccessHandler())

				// Configure logout
				.and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);
				
				**/
	}

	/**
	 * Allows access to static resources, bypassing Spring security.
	 */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(
				// client-side JS code
				"/VAADIN/**",

				// the standard favicon URI
				"/favicon.ico",

				// the robots exclusion standard
				"/robots.txt",

				// web application manifest
				"/manifest.webmanifest",
				"/sw.js",
				"/offline-page.html",

				// icons and images
				"/icons/**",
				"/images/**",

				// (development mode) H2 debugging console
				"/h2-console/**"
		);
	}
}
