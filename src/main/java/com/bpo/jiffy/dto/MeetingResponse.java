package com.bpo.jiffy.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeetingResponse {
    private Long id;
    private String title;
    private String description;
    private String meetingLink;
    private LocalDateTime meetingDateTime;
    private String scrumMasterName; // Name of the ScrumMaster who created the meeting
}