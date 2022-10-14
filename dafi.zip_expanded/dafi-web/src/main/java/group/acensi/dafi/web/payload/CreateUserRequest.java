/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.dafi.web.payload;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */

public record  CreateUserRequest (String username, String password, String role) {

}
