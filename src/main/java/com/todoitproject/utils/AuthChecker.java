package com.todoitproject.utils;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.todoitproject.dto.DtoUserLog;


@Component
public class AuthChecker {

	public DtoUserLog isBenevole() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean isUser = SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"));
		if (principal != null && principal instanceof DtoUserLog && isUser) {
			return (DtoUserLog)principal;
		} else {
			return null;
		}
	}
}
