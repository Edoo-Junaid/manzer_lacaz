/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.dafi.service.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import group.acensi.dafi.service.DateTimeConstants;
import lombok.Data;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
@Data
public class UtilisateurDto {

	private Long id;

	private RoleDto role;
	
	private String password;
	
	private String username;
	
	private boolean accountNonExpired = true;
	
	private boolean accountNonLocked = true;
	
	private boolean credentialsNonExpired = true;
	
	private boolean enabled = true;
	
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
	private ZonedDateTime lastModified;
	
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
	private ZonedDateTime created;
}
