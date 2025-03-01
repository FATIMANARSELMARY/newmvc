package com.bpo.jiffy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("SCRUM_MASTER")
@Table(name = "scrum_master")
public class ScrumMaster extends BaseUser {
    private String project;
}
