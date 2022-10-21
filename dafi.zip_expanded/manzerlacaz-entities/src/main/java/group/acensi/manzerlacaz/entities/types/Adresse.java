/**
 * 
 */
package group.acensi.manzerlacaz.entities.types;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * @author Nadeem
 *
 */
@Embeddable
@Data
public class Adresse implements Serializable {
	
	private String noAdresse;
	
	private String complementNoAdresse;
	
	private String adresseVal;
	
	private String complementAdresse;
	
	private String codePostal;
	
	private String ville;
	
	private String pays;

}
