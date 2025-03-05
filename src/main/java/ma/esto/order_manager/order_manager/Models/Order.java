package ma.esto.order_manager.order_manager.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection  = "orders")
@Data
public class Order {
    @Id
    private String orderId;
    private String customerId;
    private List<Product> items;
    private ShippingAddress shippingAddress;
    private String paymentMethod;
    private float totalAmount;
    private String currency;
    private String status;
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date createdAt;

    public Order() {
        createdAt = new Date();
    }
}
