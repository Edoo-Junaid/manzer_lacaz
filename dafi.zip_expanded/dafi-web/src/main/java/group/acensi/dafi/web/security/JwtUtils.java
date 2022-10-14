/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.dafi.web.security;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.jackson.io.JacksonSerializer;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.jodah.expiringmap.ExpiringMap;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
@Component
@Slf4j
public class JwtUtils {

	@Value("${jwt.secret}")
	private String jwtSecret;

	private Key key;

	@Value("${jwt.expiration}")
	private int jwtExpirationMs;

	private JwtParser jwtParser;

	private Map<String, String> expiringMap = null;

	@Getter
	private CompressionCodec compressionCodec = null;

	private static final String REMOTE_IP_ADDRESS = "remote-ip-address";

	private static final String ROLE = "role";

	private static final String BEARER = "Bearer ";

	private static final String AUTHORIZATION = "Authorization";
	
	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	public void init() {
		byte[] keyBytes = Decoders.BASE64.decode(this.jwtSecret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.jwtParser = Jwts.parserBuilder().setSigningKey(this.key).build();
		expiringMap = ExpiringMap.builder().expiration(jwtExpirationMs, TimeUnit.MILLISECONDS).build();
	}

	/**
	 * Generates a new token
	 * @param authentication
	 * @param request
	 * @return
	 */
	public String generateJwtToken(Authentication authentication, HttpServletRequest request) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		JwtBuilder builder = Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setId(UUID.randomUUID().toString())
				.setExpiration(new Date(System.currentTimeMillis() + this.jwtExpirationMs))
				.signWith(this.key);

		builder.serializeToJsonWith(new JacksonSerializer<>(this.objectMapper));

		builder.claim(REMOTE_IP_ADDRESS, SecurityUtils.getClientIp(request));
		builder.claim(SecurityUtils.USER_AGENT, SecurityUtils.getUserAgent(request));
		builder.claim(ROLE, userPrincipal.getRole());
		
		if (this.compressionCodec != null) {
			builder = builder.compressWith(this.compressionCodec);
		}

		String token = builder.compact();

		if (log.isDebugEnabled()) {
			log.debug("Generated new Jwt for user {}", userPrincipal.getUsername());
		}

		return token;
	}

	/**
	 * Retrieves the username form the token.
	 * 
	 * @param token
	 * @return
	 */
	public String retrieveUserNameFromJwtToken(String token) {
		return jwtParser.parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * Parses the token and also checks if its valid. Throws exceptions if not
	 * valid.
	 * 
	 * @param authToken
	 * @return
	 */
	public Jws<Claims> parseToken(String authToken) {
		return jwtParser.parseClaimsJws(authToken);
	}

	/**
	 * validates a token by first checking if the token is parsed validly and if the
	 * user making the request is the same user who created the token
	 * 
	 * @param authToken
	 * @param httpRequest
	 * @return
	 */
	public boolean validateToken(String authToken, HttpServletRequest httpRequest) {
		Jws<Claims> claims = this.parseToken(authToken);
		return (StringUtils.equals((String) claims.getBody().get(REMOTE_IP_ADDRESS), SecurityUtils.getClientIp(httpRequest))
				&& StringUtils.equals((String) claims.getBody().get(SecurityUtils.USER_AGENT), SecurityUtils.getUserAgent(httpRequest))
				&& !this.expiringMap.containsKey(claims.getBody().getId()));
	}

	/**
	 * Retrieves the token from the {@link HttpServletRequest} object
	 * 
	 * @param request
	 * @return
	 */
	public String retrieveToken(HttpServletRequest request) {
		String headerAuth = request.getHeader(AUTHORIZATION);
		if (StringUtils.isNotBlank(headerAuth) && headerAuth.startsWith(BEARER)) {
			return headerAuth.substring(BEARER.length(), headerAuth.length());
		}
		return null;
	}

	/**
	 * Invalidates the JWT token by putting it into an expiring map
	 * 
	 * @param request
	 */
	public void invalidateToken(HttpServletRequest request) {
		String token = this.retrieveToken(request);
		Jws<Claims> claims = this.parseToken(token);
		this.expiringMap.put(claims.getBody().getId(), token);
	}

}
