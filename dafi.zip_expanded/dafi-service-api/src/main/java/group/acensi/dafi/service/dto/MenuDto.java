package group.acensi.dafi.service.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import group.acensi.dafi.service.DateTimeConstants;
import lombok.Data;

@Data
public class MenuDto {
    private Long id;
    private String description;
    private String price;
    private String day;
    private String option;
    private int weekNum;
    
  
    @JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
    private ZonedDateTime lastModified;
    
    @JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
    private ZonedDateTime created;
}
