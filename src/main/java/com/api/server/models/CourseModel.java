package com.api.server.models;

import com.api.server.dtos.course.CourseRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "courses")
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer hours;
    private LocalDateTime dayAndHour;
    private Double price;
    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private List<OrderModel> orders;

    public CourseModel(CourseRequestDTO courseDTO){
        this.name = courseDTO.getName();
        this.description = courseDTO.getDescription();
        this.hours = courseDTO.getHours();
        this.dayAndHour = courseDTO.getDayAndHour();
        this.price = courseDTO.getPrice();
    }

    public void addOrder(OrderModel orderModel){
        orders.add(orderModel);
    }
}
