package ma.esto.order_manager.order_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderManagerApplication.class, args);
        System.out.println("Order Manager Application Started");
    }
}
