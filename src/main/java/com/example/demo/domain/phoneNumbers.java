package com.example.demo.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Embeddable
public class phoneNumbers {

    private long id;

    private String phone_number;
}
