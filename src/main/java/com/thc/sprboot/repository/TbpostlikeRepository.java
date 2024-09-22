package com.thc.sprboot.repository;

import com.thc.sprboot.domain.Tbpostlike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbpostlikeRepository extends JpaRepository<Tbpostlike, String> {
    Tbpostlike findByTbpostIdAndTbuserId(String tbpostId, String tbuserId);
}
