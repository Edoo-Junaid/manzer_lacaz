package group.acensi.manzerlacaz.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group.acensi.manzerlacaz.entities.Role;
import group.acensi.manzerlacaz.service.api.RoleService;
import group.acensi.manzerlacaz.service.dto.RoleDto;

@RestController
@RequestMapping("/api/auth/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //Add a Role 
    @PostMapping("/addRole")
    public ResponseEntity<Role> addMenu(@RequestBody RoleDto roleDto) {
        return new ResponseEntity<Role>(roleService.createRole(roleDto), HttpStatus.CREATED);
    }

}
