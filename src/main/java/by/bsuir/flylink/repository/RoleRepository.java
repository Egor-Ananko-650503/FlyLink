package by.bsuir.flylink.repository;

import by.bsuir.flylink.model.Role;
import by.bsuir.flylink.model.RoleName;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepository {

    Optional<Role> findByName(RoleName roleName)
}
