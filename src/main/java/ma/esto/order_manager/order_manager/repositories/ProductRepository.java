package ma.esto.order_manager.order_manager.repositories;

import ma.esto.order_manager.order_manager.Models.Product;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.repository.MongoRepository;

@Collation(value = "products")
public interface ProductRepository extends MongoRepository<Product, String> {
}
