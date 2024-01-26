package com.api.server.dtos.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDTO {
    private String name;
    private String description;
    private Integer hours;
    private LocalDateTime dayAndHour;
    private Double price;
}
