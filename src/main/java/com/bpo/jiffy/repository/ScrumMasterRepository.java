package com.bpo.jiffy.repository;

import com.bpo.jiffy.entity.ScrumMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScrumMasterRepository extends JpaRepository<ScrumMaster, Long> {
    Optional<ScrumMaster> findByProject(String project);

    Optional<ScrumMaster> findByEmail(String email);
}