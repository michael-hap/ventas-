package gestionventas.Config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

    private final String secretKey =
            "gestionventasclaveultrasecreta2026gestionventas";

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generarToken(String correo, String rol) {

        return Jwts.builder()
                .setSubject(correo)
                .claim("role", rol)
                .setIssuedAt(new Date())
                .setExpiration(
                        Date.from(
                                Instant.now()
                                        .plus(24, ChronoUnit.HOURS)
                        )
                )
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extraerCorreo(String token) {

        return extraerClaims(token)
                .getSubject();
    }

    public String extraerRol(String token) {

        return extraerClaims(token)
                .get("role", String.class);
    }

    private Claims extraerClaims(String token) {

        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean esValido(String token) {

        try {

            extraerClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}