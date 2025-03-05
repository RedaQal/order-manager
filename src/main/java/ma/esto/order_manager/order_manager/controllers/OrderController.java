package ma.esto.order_manager.order_manager.controllers;

import ma.esto.order_manager.order_manager.Models.Order;
import ma.esto.order_manager.order_manager.repositories.OrderRepository;
import ma.esto.order_manager.order_manager.services.MqttMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class OrderController {
    private final MqttMessageService mqttMessageService;
    private final OrderRepository orderRepository;

    public OrderController(MqttMessageService mqttMessageService, OrderRepository orderRepository) {
        this.mqttMessageService = mqttMessageService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

}
