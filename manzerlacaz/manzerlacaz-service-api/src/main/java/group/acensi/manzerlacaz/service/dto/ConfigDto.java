package group.acensi.manzerlacaz.service.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import group.acensi.manzerlacaz.service.DateTimeConstants;
import lombok.Data;

@Data
public class ConfigDto {
    private Long id;
    private String name;
    private String value;

  
    @JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
    private ZonedDateTime lastModified;
    
    @JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
    private ZonedDateTime created;
}
