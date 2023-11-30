package com.example.testbizibox.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "call_data")
public class CallData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "time_now")
    private LocalDateTime time;

    @Column(name = "call_type")
    private String callType;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "save_contact")
    private Boolean saveContact;

}
