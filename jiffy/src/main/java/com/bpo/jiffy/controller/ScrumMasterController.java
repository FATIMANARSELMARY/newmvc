package com.bpo.jiffy.controller;

import com.bpo.jiffy.entity.ScrumMaster;
import com.bpo.jiffy.service.ScrumMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scrummaster")
@RequiredArgsConstructor
public class ScrumMasterController {
    private final ScrumMasterService scrumMasterService;

    @GetMapping
    public List<ScrumMaster> getScrumMasters() {
        return scrumMasterService.getAllScrumMasters();
    }
}
