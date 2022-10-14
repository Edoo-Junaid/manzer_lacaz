/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */

package group.acensi.dafi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.acensi.dafi.entities.Collaborateur;

/**
 * DAO
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 */
@Repository
public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long> {

}
