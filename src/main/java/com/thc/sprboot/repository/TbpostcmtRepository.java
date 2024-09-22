package com.thc.sprboot.repository;

import com.thc.sprboot.domain.Tbpostcmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbpostcmtRepository extends JpaRepository<Tbpostcmt, String> {
}
