package group.acensi.manzerlacaz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.acensi.manzerlacaz.dao.RoleRepository;
import group.acensi.manzerlacaz.entities.Role;
import group.acensi.manzerlacaz.service.api.RoleService;
import group.acensi.manzerlacaz.service.dto.RoleDto;
import group.acensi.manzerlacaz.service.mapper.RoleMapper;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role createRole(RoleDto roleDto) {
        Role role = RoleMapper.INSTANCE.toEntity(roleDto);
        return roleRepository.save(role);
    }

}
