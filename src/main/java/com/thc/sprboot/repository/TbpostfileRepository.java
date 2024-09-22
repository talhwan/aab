package com.thc.sprboot.repository;

import com.thc.sprboot.domain.Tbpostfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbpostfileRepository extends JpaRepository<Tbpostfile, String> {
}
