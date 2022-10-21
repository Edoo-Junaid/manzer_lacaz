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
public class Email implements Serializable {
	
    private String emailPerso;
    
    private String emailPro;

}
