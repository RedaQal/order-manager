package ma.esto.order_manager.order_manager.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.esto.order_manager.order_manager.Models.Order;
import ma.esto.order_manager.order_manager.repositories.OrderRepository;
import ma.esto.order_manager.order_manager.services.MqttMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Tag(name = "Order API", description = "APIs for managing orders")
public class OrderController {
    private final MqttMessageService mqttMessageService;
    private final OrderRepository orderRepository;

    public OrderController(MqttMessageService mqttMessageService, OrderRepository orderRepository) {
        this.mqttMessageService = mqttMessageService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    @Operation(summary = "Get all orders", description = "Retrieve a list of all orders")
    @ApiResponse(responseCode = "200", description = "Orders retrieved successfully")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @PostMapping("/orders")
    @Operation(summary = "Insert new Order", description = "Insert a new order after sending it to the mqtt broker")
    @ApiResponse(responseCode = "201", description = "Order created")
    public ResponseEntity<String> order(@RequestBody Order order) {
        mqttMessageService.sendMessage(order);
        return new ResponseEntity<>("Order created", HttpStatus.CREATED);
    }
}
