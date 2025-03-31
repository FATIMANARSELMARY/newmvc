package com.bpo.jiffy.repository;

import com.bpo.jiffy.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    // Find all meetings created by a specific ScrumMaster
    List<Meeting> findByScrumMasterId(Long scrumMasterId);
}