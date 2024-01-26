package com.api.server.services;

import com.api.server.dtos.course.CourseResponseDTO;
import com.api.server.dtos.payment.*;
import com.api.server.dtos.payment.request.IdentificationDTO;
import com.api.server.dtos.payment.request.PayerDTO;
import com.api.server.dtos.payment.request.PaymentRequestDTO;
import com.api.server.dtos.payment.response.PaymentResponseDTO;
import com.api.server.exceptions.ErrorInWebClientHttpRequestException;
import com.api.server.models.CourseModel;
import com.api.server.models.OrderModel;
import com.api.server.models.Status;
import com.api.server.models.UserModel;
import com.api.server.repositories.CourseRepository;
import com.api.server.repositories.OrderRepository;
import com.api.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient webClient;

    public String addOrder(String userId, Long courseId){

        UserModel user = userRepository.findById(userId).orElseThrow();

        CourseModel course = courseRepository.findById(courseId).orElseThrow();

        try{
            OrderModel order = new OrderModel();

            order.addCourse(course);
            order.addUser(user);
            order.setStatus(Status.PENDING);
            order.setTotal(order.getTotal());

            orderRepository.save(order);
            return "Pedido criado";
        }catch (Exception e){
            return "Erro ao criar o pedido";
        }
    }

    public PaymentResponseDTO payOrder(String userId, String orderId) {
        UserModel userModel = userRepository.findById(userId).orElseThrow();

        OrderModel orderModel = orderRepository.findById(orderId).orElseThrow();

        PaymentRequestDTO payment = new PaymentRequestDTO(
                "Payment payment related to "+userModel.getFirstName()+" "+userModel.getLastName(),
                orderModel.getId(),
                new PayerDTO(userModel.getEmail(), new IdentificationDTO("CPF", userModel.getCpf())),
                "pix",
                ZonedDateTime.now().plusMinutes(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")),
                orderModel.getTotal()
        );

        return webClient.post()
                  .contentType(MediaType.APPLICATION_JSON)
                  .body(BodyInserters.fromValue(payment))
                  .retrieve()
                  .onStatus(httpStatus -> !httpStatus.is2xxSuccessful(), clientResponse -> {
                      return clientResponse.bodyToMono(ErrorBodyDTO.class)
                              .flatMap(errorBody -> {
                                  return Mono.error(new ErrorInWebClientHttpRequestException(errorBody.getMessage()));
                              });
                  })
                  .bodyToMono(PaymentResponseDTO.class)
                  .block();

    }

    public String confirmationPayment(String orderId){
        OrderModel orderModel = orderRepository.findById(orderId).orElseThrow();

        orderModel.setStatus(Status.APPROVED);
        orderRepository.save(orderModel);
        System.out.println("------------------Aprovado--------------------------------");
        return "Pagamento realizado";
    }

    public List<CourseResponseDTO> getCoursesById(String id){
        OrderModel orderModel = orderRepository.findById(id).orElseThrow();

        return orderModel.getCourses().stream().map(course -> new CourseResponseDTO(course)).toList();
    }

}
