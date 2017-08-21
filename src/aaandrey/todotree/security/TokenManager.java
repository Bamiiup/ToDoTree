package aaandrey.todotree.security;

import java.util.Date;

public class TokenManager {
	private String privateKey;
	
	public TokenManager(String privateKey) {
		this.privateKey = privateKey;
	}

	public String createToken(TokenPayload payload) {
		String mixedPayload = createMixedTokenPayload(payload);
		String sign = createSign(mixedPayload);
		String token = String.format("%s#%s", mixedPayload, sign);
		return token;
	}
	
	private String createMixedTokenPayload(TokenPayload payload) {
		String createdTime = String.valueOf(payload.getCreatedTime().getTime());
		String id = String.valueOf(payload.getUserId());
		String login = payload.getLogin();
		String mix = String.format("%s#%s#%s", id, login, createdTime);
		
		return mix;
	}
	
	//TODO: сделать реальную электрунную подпись!
	//так делать нельзя
	private String createSign(String mixedPayload) {
		return "" + mixedPayload.charAt(0) + privateKey.charAt(2) + mixedPayload.charAt(4) + mixedPayload.charAt(7);
	}
	
	public boolean verifyToken(String token) {
		TokenPayload payload = extractPayload(token);
		String trustedToken = createToken(payload);
		return token.equals(trustedToken);
	}
	
	public TokenPayload extractPayload(String token) {
		String[] tokenParts = token.split("#");
		Date createdTime = new Date(Long.valueOf(tokenParts[0]));
		Long id = Long.valueOf(tokenParts[1]);
		String login = tokenParts[2];
		
		TokenPayload payload = new TokenPayload(id, login, createdTime);
		
		return payload;
	}
}
