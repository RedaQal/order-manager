package ma.esto.order_manager.order_manager.Models;

import lombok.Data;

@Data
public class Product {
    private String productId;
    private String productName;
    private int quantity;
    private float unitPrice;
    private String currency;
}