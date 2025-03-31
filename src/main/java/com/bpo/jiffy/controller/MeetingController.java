package com.bpo.jiffy.controller;

import com.bpo.jiffy.dto.MeetingRequest;
import com.bpo.jiffy.dto.MeetingResponse;
import com.bpo.jiffy.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    // Create a new meeting
    @PostMapping("/scrum-master/create/{scrumMasterId}")
    public MeetingResponse createMeeting(
            @PathVariable Long scrumMasterId,
            @RequestBody MeetingRequest request) {
        return meetingService.createMeeting(scrumMasterId, request);
    }

    // Get all meetings for a ScrumMaster
    @GetMapping("/scrum-master/readAll/{scrumMasterId}")
    public List<MeetingResponse> getAllMeetingsByScrumMaster(
            @PathVariable Long scrumMasterId) {
        return meetingService.getAllMeetingsByScrumMaster(scrumMasterId);
    }

    // Update a meeting
    @PutMapping("/scrum-master/update/{meetingId}")
    public MeetingResponse updateMeeting(
            @PathVariable Long meetingId,
            @RequestBody MeetingRequest request) {
        return meetingService.updateMeeting(meetingId, request);
    }

    // Delete a meeting
    @DeleteMapping("/scrum-master/delete/{meetingId}")
    public void deleteMeeting(@PathVariable Long meetingId) {
        meetingService.deleteMeeting(meetingId);
    }

    // Get all meetings for employees
    @GetMapping("/readAll")
    public List<MeetingResponse> getAllMeetingsForEmployees() {
        return meetingService.getAllMeetings();
    }

    // Get all meetings for an employee
    @GetMapping("/employee/{employeeId}")
    public List<MeetingResponse> getMeetingsForEmployee(
            @PathVariable Long employeeId) {
        return meetingService.getMeetingsForEmployee(employeeId);
    }

}