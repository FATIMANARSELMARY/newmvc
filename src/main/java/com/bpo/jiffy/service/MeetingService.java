package com.bpo.jiffy.service;

import com.bpo.jiffy.dto.MeetingRequest;
import com.bpo.jiffy.dto.MeetingResponse;
import com.bpo.jiffy.entity.Employee;
import com.bpo.jiffy.entity.Meeting;
import com.bpo.jiffy.entity.ScrumMaster;
import com.bpo.jiffy.repository.EmployeeRepository;
import com.bpo.jiffy.repository.MeetingRepository;
import com.bpo.jiffy.repository.ScrumMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final ScrumMasterRepository scrumMasterRepository;
    private final EmployeeRepository employeeRepository;

    // Create a new meeting
    public MeetingResponse createMeeting(Long scrumMasterId, MeetingRequest request) {
        ScrumMaster scrumMaster = scrumMasterRepository.findById(scrumMasterId)
                .orElseThrow(() -> new RuntimeException("ScrumMaster not found"));

        Meeting meeting = new Meeting();
        meeting.setTitle(request.getTitle());
        meeting.setDescription(request.getDescription());
        meeting.setMeetingLink(request.getMeetingLink());
        meeting.setMeetingDateTime(request.getMeetingDateTime());
        meeting.setScrumMaster(scrumMaster);

        Meeting savedMeeting = meetingRepository.save(meeting);
        return mapToMeetingResponse(savedMeeting);
    }

    // Get all meetings for a ScrumMaster
    public List<MeetingResponse> getAllMeetingsByScrumMaster(Long scrumMasterId) {
        List<Meeting> meetings = meetingRepository.findByScrumMasterId(scrumMasterId);
        return meetings.stream()
                .map(this::mapToMeetingResponse)
                .collect(Collectors.toList());
    }

    // Update a meeting
    public MeetingResponse updateMeeting(Long meetingId, MeetingRequest request) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));

        meeting.setTitle(request.getTitle());
        meeting.setDescription(request.getDescription());
        meeting.setMeetingLink(request.getMeetingLink());
        meeting.setMeetingDateTime(request.getMeetingDateTime());

        Meeting updatedMeeting = meetingRepository.save(meeting);
        return mapToMeetingResponse(updatedMeeting);
    }

    // Delete a meeting
    public void deleteMeeting(Long meetingId) {
        meetingRepository.deleteById(meetingId);
    }

    // Get all meetings for an employee (filtered by their ScrumMaster)
    public List<MeetingResponse> getMeetingsForEmployee(Long employeeId) {
        // Find the employee
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Log the employee and their ScrumMaster
        System.out.println("Employee ID: " + employee.getId() + ", ScrumMaster ID: " + employee.getScrumMaster().getId());

        // Get the ScrumMaster assigned to the employee
        ScrumMaster scrumMaster = employee.getScrumMaster();

        // Fetch meetings created by the ScrumMaster
        List<Meeting> meetings = meetingRepository.findByScrumMasterId(scrumMaster.getId());

        // Log the number of meetings found
        System.out.println("Number of meetings found: " + meetings.size());

        // Map to MeetingResponse DTO
        return meetings.stream()
                .map(this::mapToMeetingResponse)
                .collect(Collectors.toList());
    }

    // Get all meetings
    public List<MeetingResponse> getAllMeetings() {
        List<Meeting> meetings = meetingRepository.findAll();
        return meetings.stream()
                .map(this::mapToMeetingResponse)
                .collect(Collectors.toList());
    }

    // Map Meeting entity to MeetingResponse DTO
    private MeetingResponse mapToMeetingResponse(Meeting meeting) {
        MeetingResponse response = new MeetingResponse();
        response.setId(meeting.getId());
        response.setTitle(meeting.getTitle());
        response.setDescription(meeting.getDescription());
        response.setMeetingLink(meeting.getMeetingLink());
        response.setMeetingDateTime(meeting.getMeetingDateTime());
        response.setScrumMasterName(meeting.getScrumMaster().getName());
        return response;
    }
}