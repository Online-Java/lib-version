package uz.online.libversion.security;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@Log4j2
public class JwtUtil {

    @Value("${secret.key}")
    private String secretKey;

    public String generateToken(String session) {
        return Jwts.builder()
                .setSubject(session)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public boolean isValid(String token) {
        try {
            if (!parser().isSigned(token)) {
                return false;
            }
            return StringUtils.hasText(parser()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject());
        } catch (Exception e) {
            log.error("Error validating auth token! throwable:{}, message:{}", e.getClass(), e.getMessage());
            return false;
        }
    }

    public <T> T getClaims(String name, String token, Class<T> type) {
        return parser()
                .parseClaimsJws(token)
                .getBody()
                .get(name, type);
    }

    private JwtParser parser() {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build();
    }
}
