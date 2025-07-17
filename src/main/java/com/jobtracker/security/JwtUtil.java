package com.jobtracker.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

	private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours
	private static final String SECRET = "myjobtrackerjwtsecretkeymyjobtrackerjwtsecretkey"; // min 256-bit key

	private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

	// Generate JWT Token
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}

	// Validate token
	public boolean isTokenValid(String token, String username) {
		String extractedUsername = extractUsername(token);
		return (username.equals(extractedUsername) && !isTokenExpired(token));
	}

	// Extract username
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	// Check expiration
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	// Extract expiration
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	// Generic claim extractor
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claimsResolver.apply(claims);
	}
}
