package group.acensi.manzerlacaz.service.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import group.acensi.manzerlacaz.entities.Menu;
import group.acensi.manzerlacaz.entities.Utilisateur;
import group.acensi.manzerlacaz.service.DateTimeConstants;
import lombok.Data;

@Data
public class OrderDto {

    private Long id;
    private Long user_id;
    private Long menu_id;
 
	private long payment;
	private String option;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
	private ZonedDateTime lastModified;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
	private ZonedDateTime created;
}
