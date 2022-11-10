package group.acensi.manzerlacaz.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group.acensi.manzerlacaz.entities.Config;
import group.acensi.manzerlacaz.service.api.ConfigService;
import group.acensi.manzerlacaz.service.dto.ConfigDto;

@RestController
@RequestMapping("/api/auth/config")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ConfigController {
    @Autowired
    private ConfigService configService;

    @PostMapping("/addPresetTime")
    public ResponseEntity<Config> addPresetTime(@RequestBody ConfigDto configDto) {
        return new ResponseEntity<Config>(configService.createConfiguration(configDto), HttpStatus.CREATED);
    }
    
    @PostMapping("/getPresetTime")
    public String getPresetTime() {
        return configService.listPresetValue();
    }

}
