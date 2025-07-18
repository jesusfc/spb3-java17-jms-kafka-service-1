package com.jesusfc.demo.database.repository;

import com.jesusfc.demo.database.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jesusfc
 * Created on mar 2023
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
