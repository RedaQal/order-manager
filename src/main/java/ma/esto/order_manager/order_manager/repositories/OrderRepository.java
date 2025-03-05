package ma.esto.order_manager.order_manager.repositories;

import ma.esto.order_manager.order_manager.Models.Order;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Collation(value = "orders")
public interface OrderRepository extends MongoRepository<Order, String> {
}