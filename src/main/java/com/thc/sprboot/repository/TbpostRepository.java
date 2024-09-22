package com.thc.sprboot.repository;

import com.thc.sprboot.domain.Tbpost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbpostRepository extends JpaRepository<Tbpost, String> {
}
