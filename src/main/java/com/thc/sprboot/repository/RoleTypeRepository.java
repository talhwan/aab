package com.thc.sprboot.repository;

import com.thc.sprboot.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleTypeRepository extends JpaRepository<RoleType, String>{
	RoleType findByTypeName(String typeName);
}
