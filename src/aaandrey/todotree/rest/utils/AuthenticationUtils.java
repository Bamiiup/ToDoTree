package aaandrey.todotree.rest.utils;

import java.util.function.Function;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import aaandrey.todotree.security.TokenManager;
import aaandrey.todotree.security.TokenPayload;

@Component("authenticationUtils")
public class AuthenticationUtils {
	@Resource(name = "tokenManager")
	private TokenManager tokenManager;
	
	public <R> ResponseEntity<R> peformAfterAuthentication(HttpServletRequest request,
			Function<Long, ResponseEntity<R>> function) {
		String token = request.getHeader("token");

		if (!tokenManager.verifyToken(token)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		TokenPayload tokenPayload = tokenManager.extractPayload(token);

		return function.apply(tokenPayload.getUserId());
	}
}
