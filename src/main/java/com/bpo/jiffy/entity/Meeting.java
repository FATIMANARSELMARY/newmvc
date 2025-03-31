package com.bpo.jiffy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "meetings")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // Meeting title
    private String description; // Meeting description
    private String meetingLink; // Meeting link (e.g., Google Meet, Zoom)
    private LocalDateTime meetingDateTime; // Date and time of the meeting

    @ManyToOne
    @JoinColumn(name = "scrum_master_id", nullable = false)
    private ScrumMaster scrumMaster; // ScrumMaster who created the meeting
}