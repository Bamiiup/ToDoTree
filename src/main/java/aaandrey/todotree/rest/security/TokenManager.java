package aaandrey.todotree.rest.security;

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

    //REVIEW: заменить на реальный алгоритм электронной подписи.
    private String createSign(String mixedPayload) {
        return String.valueOf(mixedPayload.hashCode());
    }

    public boolean verifyToken(String token) {
        TokenPayload payload = extractPayload(token);
        String trustedToken = createToken(payload);
        return token.equals(trustedToken);
    }

    public TokenPayload extractPayload(String token) {
        String[] tokenParts = token.split("#");
        Long id = Long.valueOf(tokenParts[0]);
        String login = tokenParts[1];
        Date createdTime = new Date(Long.valueOf(tokenParts[2]));

        TokenPayload payload = new TokenPayload(id, login, createdTime);

        return payload;
    }
}
