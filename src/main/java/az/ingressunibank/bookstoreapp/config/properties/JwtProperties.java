package az.ingressunibank.bookstoreapp.config.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtProperties {
    @Value("${application.security.jwt.token-header}")
    private String tokenHeader;

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.access-token-expiration-time}")
    private Long accessTokenExpirationTime;

    @Value("${application.security.jwt.refresh-token-expiration-time}")
    private Long refreshTokenExpirationTime;
}
