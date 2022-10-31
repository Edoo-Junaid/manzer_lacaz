package group.acensi.manzerlacaz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.acensi.manzerlacaz.dao.ConfigRepository;
import group.acensi.manzerlacaz.entities.Config;
import group.acensi.manzerlacaz.service.api.ConfigService;
import group.acensi.manzerlacaz.service.dto.ConfigDto;
import group.acensi.manzerlacaz.service.mapper.ConfigMapper;
import group.acensi.manzerlacaz.service.mapper.MenuMapper;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    @Override
    public Config createConfiguration(ConfigDto configDto) {
        
        if (configRepository.checkIfConfigExists(configDto.getName())==true) {
            long configId = configRepository.getConfigurationId();
            configDto.setId(configId);

        }

        Config config = ConfigMapper.INSTANCE.toEntity(configDto);
        return configRepository.save(config);
    }

    @Override
    public String listPresetValue() {
        return configRepository.presetTime();
    }

}
