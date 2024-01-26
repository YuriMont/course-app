package com.api.server.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalDateTime generationDate;
    private Double total;
    private Status status;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders_courses",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<CourseModel> courses;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders_user",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserModel> users;

    public OrderModel() {
        total = 0.0;
        generationDate = LocalDateTime.now();
        status = Status.PENDING;
        users = new ArrayList<>();
        courses = new ArrayList<>();
    }

    public Double getTotal() {
        for(CourseModel course: courses){
            total += course.getPrice();
        }
        return total;
    }

    public void addUser(UserModel user){
        users.add(user);
    }

    public void addCourse(CourseModel courseModel){
        courses.add(courseModel);
    }
}
