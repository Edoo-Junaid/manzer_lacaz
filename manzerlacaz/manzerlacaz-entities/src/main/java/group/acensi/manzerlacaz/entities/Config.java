package group.acensi.manzerlacaz.entities;

import group.acensi.manzerlacaz.entities.base.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Config extends AbstractEntity {
    private String name;
    private String value;

}
