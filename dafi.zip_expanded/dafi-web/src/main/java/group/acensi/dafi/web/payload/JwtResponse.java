/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.dafi.web.payload;

import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import group.acensi.dafi.service.DateTimeConstants;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
public record JwtResponse(String token, String type, Long id, String username, String email, List<String> authorities,
		String role, int tokenLength, String tokenCompression, @JsonFormat
	      (shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)ZonedDateTime dateTime) {

}
