/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.manzerlacaz.web.controller;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import group.acensi.manzerlacaz.service.api.UtilisateurService;
import group.acensi.manzerlacaz.service.dto.RoleDto;
import group.acensi.manzerlacaz.service.dto.UtilisateurDto;
import group.acensi.manzerlacaz.web.payload.CreateUserRequest;
import group.acensi.manzerlacaz.web.payload.JwtResponse;
import group.acensi.manzerlacaz.web.payload.LoginRequest;
import group.acensi.manzerlacaz.web.security.JwtUtils;
import group.acensi.manzerlacaz.web.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * Controller for authentication
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtUtils.generateJwtToken(authentication, request);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		JwtResponse jwtResponse = new JwtResponse(
				token,
				null,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(),
				userDetails.getRole(),
				token.length(),
				this.jwtUtils.getCompressionCodec() == null? null: this.jwtUtils.getCompressionCodec().getAlgorithmName(),
				ZonedDateTime.now()
				);

		
		return ResponseEntity.ok(jwtResponse);
	}
	
	@PostMapping("/createUser")
	public Long createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
		
		UtilisateurDto utilisateurDto = new UtilisateurDto();
		utilisateurDto.setUsername(createUserRequest.username());
		utilisateurDto.setPassword(passwordEncoder.encode(createUserRequest.password()));
		RoleDto roleDto = new RoleDto();
		roleDto.setNom(createUserRequest.role());
		utilisateurDto.setRole(roleDto);
		
		return this.utilisateurService.createUtilisateur(utilisateurDto);

	}
	
	@GetMapping("/refreshToken")
	public ResponseEntity<String> refreshToken(HttpServletRequest request) {
		return ResponseEntity.ok(this.jwtUtils.generateJwtToken(SecurityContextHolder.getContext().getAuthentication(), request));
	}
	
	@GetMapping("/decodeJwtToken")
	public ResponseEntity<Jws<Claims>> decodeJwtToken(@RequestParam String token) {
		
		return ResponseEntity.ok(jwtUtils.parseToken(token));
		
	}
	
	@GetMapping("/logout")
	public void logout(HttpServletRequest request) {
		jwtUtils.invalidateToken(request);
		
	}

}
