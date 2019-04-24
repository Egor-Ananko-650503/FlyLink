package by.bsuir.flylink.service.impl;

import by.bsuir.flylink.model.Role;
import by.bsuir.flylink.model.RoleName;
import by.bsuir.flylink.repository.RoleRepository;
import by.bsuir.flylink.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}
