package com.api.server.dtos.course;

import com.api.server.models.CourseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CourseResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Integer hours;
    private LocalDateTime dayAndHour;
    private Double price;

    public CourseResponseDTO(CourseModel course){
        this.id = course.getId();
        this.name = course.getName();
        this.description = course.getDescription();
        this.hours = course.getHours();
        this.dayAndHour = course.getDayAndHour();
        this.price = course.getPrice();
    }
}
