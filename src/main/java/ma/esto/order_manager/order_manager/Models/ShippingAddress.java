package ma.esto.order_manager.order_manager.Models;

import lombok.Data;

@Data
public class ShippingAddress {
    private String street;
    private String city;
    private String postalCode;
    private String country;
}
