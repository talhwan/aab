package com.thc.sprboot.repository;

import com.thc.sprboot.domain.Tbnotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbnoticeRepository extends JpaRepository<Tbnotice, String> {
}
