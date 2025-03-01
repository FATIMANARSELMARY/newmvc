package com.bpo.jiffy.repository;

import com.bpo.jiffy.entity.ScrumMaster;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrumMasterRepository extends JpaRepository<ScrumMaster, Long> {
    Optional<ScrumMaster> findByEmail(String email);
}
