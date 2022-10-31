package group.acensi.manzerlacaz.service.api;

import group.acensi.manzerlacaz.entities.Config;
import group.acensi.manzerlacaz.service.dto.ConfigDto;

public interface ConfigService {
    public Config createConfiguration(ConfigDto configDto);

    String listPresetValue();
}
