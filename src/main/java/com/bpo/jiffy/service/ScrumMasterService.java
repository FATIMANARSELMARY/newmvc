package com.bpo.jiffy.service;

import com.bpo.jiffy.entity.ScrumMaster;
import com.bpo.jiffy.repository.ScrumMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrumMasterService {
    private final ScrumMasterRepository scrumMasterRepository;

    public List<ScrumMaster> getAllScrumMasters() {
        return scrumMasterRepository.findAll();
    }
}
