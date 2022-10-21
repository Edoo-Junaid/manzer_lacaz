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
public class Telephone implements Serializable {
	
    private String telFixePerso;
    
    private String telFixePro;
    
    private String telMobPerso;
    
    private String telMobPro;

}
