package by.bsuir.flylink.service;

import by.bsuir.flylink.model.Role;
import by.bsuir.flylink.model.RoleName;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleName roleName);
}
