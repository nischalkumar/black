package org.wizindia.black.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.wizindia.black.worker.UserWorker;

@Configuration
public class OAuth2ServerConfiguration {

	private static final String RESOURCE_ID = "restservice";
    private static final int accessTokenValiditySeconds = 2592000; //30 days

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends
			ResourceServerConfigurerAdapter {

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			// @formatter:off
			resources
				.resourceId(RESOURCE_ID);
			// @formatter:on
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http
				.authorizeRequests()
					.antMatchers("/v1/user/**").authenticated()
					.antMatchers("/v1/subject/**").authenticated();
			// @formatter:on
		}

	}

	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends
			AuthorizationServerConfigurerAdapter {

		private TokenStore tokenStore = new InMemoryTokenStore();

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Autowired
		private UserWorker userWorker;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints)
				throws Exception {
			// @formatter:off
			endpoints
				.tokenStore(this.tokenStore)
				.authenticationManager(this.authenticationManager);
			// @formatter:on
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			// @formatter:off
			clients
				.inMemory()
					.withClient("clientapp")
						.authorizedGrantTypes("password", "refresh_token")
						.authorities("USER")
						.scopes("read", "write")
						.resourceIds(RESOURCE_ID)
                        .accessTokenValiditySeconds(accessTokenValiditySeconds)
                        .refreshTokenValiditySeconds(accessTokenValiditySeconds)
						.secret("123456");
			// @formatter:on
		}

		@Bean
		@Primary
		public DefaultTokenServices tokenServices() {
			DefaultTokenServices tokenServices = new DefaultTokenServices();
			tokenServices.setSupportRefreshToken(true);
			tokenServices.setTokenStore(this.tokenStore);
			return tokenServices;
		}

	}

}
