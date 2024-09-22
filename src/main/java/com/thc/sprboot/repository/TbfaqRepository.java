package com.thc.sprboot.repository;

import com.thc.sprboot.domain.Tbfaq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbfaqRepository extends JpaRepository<Tbfaq, String> {
    Tbfaq findBySequence(Integer sequence);
}
