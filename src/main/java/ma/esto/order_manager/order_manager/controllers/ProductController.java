package ma.esto.order_manager.order_manager.controllers;

import ma.esto.order_manager.order_manager.Models.Product;
import ma.esto.order_manager.order_manager.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class ProductController {
    private final ProductRepository productRepository;
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping("/products")
    public ResponseEntity<String> products(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>("Product saved", HttpStatus.CREATED);
    }
}
