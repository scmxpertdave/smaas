package com.SCMXPert.sbmongodb.securityOAuth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import com.SCMXPert.sbmongodb.document.Role;
import com.SCMXPert.sbmongodb.repository.RoleRepository;

@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Autowired
	RoleRepository roleRepo;

	public String[] getRolePermissions(String[] roleNames) {
		String[] roles = null;
		for (String roleName : roleNames) {
			Role role = roleRepo.findByRole(roleName);
			roles = role.getRolePermissions();
		}

		return roles;

	}

	public String[] getRolePermission(String roleName) {
		Role role = roleRepo.findByRole(roleName);
		String[] roles = role.getRolePermissions();
		return roles;

	}

	String[] ConstantMultiForAdmin = { "ADMIN", "USER","SUPERADMIN"};
	String[] ConstntUser = { "USER","GOODSRECEIPIENT" };

	@Override
	public void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
	//.antMatchers(getRolePermission("ROLE_ADMIN")).hasAnyAuthority("ROLE_ADMIN")
							.antMatchers(getRolePermission("SUPERADMIN")).hasAnyAuthority("SUPERADMIN")
				//		.requestMatchers(getRolePermission("GOODSRECEIPIENT")).hasAnyAuthority("GOODSRECEIPIENT")
						//	.antMatchers(getRolePermission("ROLE_USER")).hasAnyAuthority("ROLE_ADMIN", "ROLE_USER","SUPERADMIN")
							.and()
							.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())

		;
	}
}
