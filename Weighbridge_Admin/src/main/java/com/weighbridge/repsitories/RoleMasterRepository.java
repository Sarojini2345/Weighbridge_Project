package com.weighbridge.repsitories;

import com.weighbridge.entities.RoleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface RoleMasterRepository extends JpaRepository<RoleMaster, Integer> {
    RoleMaster findByRoleName(String role);

    String findRoleNameByRoleId(int roleId);

    @Query("SELECT r.roleName FROM RoleMaster r")
    List<String> findAllRoleListName();

    Iterable<RoleMaster> findAllByRoleNameIn(Set<String> setOfRoles);
}
