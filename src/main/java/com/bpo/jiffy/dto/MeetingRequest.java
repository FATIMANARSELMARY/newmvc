package com.bpo.jiffy.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeetingRequest {
    private String title;
    private String description;
    private String meetingLink;
    private LocalDateTime meetingDateTime;
}