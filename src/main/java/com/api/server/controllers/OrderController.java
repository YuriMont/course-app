package com.api.server.controllers;

import com.api.server.exceptions.ErrorInWebClientHttpRequestException;
import com.api.server.infra.security.TokenService;
import com.api.server.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/add/{courseId}")
    public ResponseEntity<?> addOrder(@RequestHeader("Authorization") String token, @PathVariable Long courseId){
        String userId = tokenService.validateToken(token.replace("Bearer ", ""));
        return ResponseEntity.ok().body(orderService.addOrder(userId, courseId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getCourses(@PathVariable String orderId){
        return ResponseEntity.ok().body(orderService.getCoursesById(orderId));
    }

    @GetMapping("/pay/{orderId}")
    public ResponseEntity<?> payOrder(@RequestHeader("Authorization") String token, @PathVariable String orderId) {
        String userId = tokenService.validateToken(token.replace("Bearer ", ""));

        return ResponseEntity.ok(orderService.payOrder(userId, orderId));
    }

    @PostMapping("/confirmation/{orderId}")
    public ResponseEntity<?> confirmationPayment(@PathVariable String orderId){
        return ResponseEntity.ok().body(orderService.confirmationPayment(orderId));
    }

}
