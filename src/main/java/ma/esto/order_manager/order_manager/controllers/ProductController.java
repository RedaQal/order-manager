package ma.esto.order_manager.order_manager.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.esto.order_manager.order_manager.Models.Product;
import ma.esto.order_manager.order_manager.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "Product API", description = "APIs for managing products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    @Operation(summary = "Get all products", description = "Retrieve a list of all products")
    @ApiResponse(responseCode = "200", description = "products retrieved successfully")
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping("/products")
    @Operation(summary = "Insert new Product", description = "Insert a new product directly to the db")
    @ApiResponse(responseCode = "201", description = "Product saved")
    public ResponseEntity<String> products(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>("Product saved", HttpStatus.CREATED);
    }
}
