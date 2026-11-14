package com.mfplatform.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfplatform.user.entity.Role;
import com.mfplatform.user.enums.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RoleName investor);

    boolean existsByName(RoleName name);

}
