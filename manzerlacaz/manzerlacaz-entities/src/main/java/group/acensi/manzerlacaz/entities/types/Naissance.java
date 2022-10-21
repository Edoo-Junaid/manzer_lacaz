/**
 * 
 */
package group.acensi.manzerlacaz.entities.types;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * @author Nadeem
 *
 */
@Embeddable
@Data
public class Naissance implements Serializable {
	
    private LocalDate dateNaissance;
    
    private String villeNaissance;
    
    private String departementNaissance;
    
    private String paysNaissance;

}
