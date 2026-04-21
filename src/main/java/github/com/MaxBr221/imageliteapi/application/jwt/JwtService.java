package github.com.MaxBr221.imageliteapi.application.jwt;

import github.com.MaxBr221.imageliteapi.domain.AccessToken;
import github.com.MaxBr221.imageliteapi.domain.entity.User;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private final SecreteKeyGenerator keyGenerator;

    public JwtService(SecreteKeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    public AccessToken generateToken(User user){
        SecretKey key = keyGenerator.getKey();
        Date expirationDate = generateExpirationDate();
        var claims = generateTokenClaims(user);

       String token = Jwts
               .builder()
               .signWith(key)
               .subject(user.getEmail())
               .expiration(expirationDate)
               .claims(claims)
               .compact();

       return new AccessToken(token);
   }

   private Date generateExpirationDate(){
        var minuteExpiration = 60;
        LocalDateTime now = LocalDateTime.now().plusMinutes(minuteExpiration);
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
       //para adpatar de acordo com a "zoneId" (pais) do servidor
   }
   private Map<String, Object> generateTokenClaims(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", user.getName());
        return claims;
   }
}
