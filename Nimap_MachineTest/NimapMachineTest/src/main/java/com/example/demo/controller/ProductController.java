package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.Category;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public Page<Product> getAll(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return productRepository.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Optional<Category> category = categoryRepository.findById(product.getCategory().getId());
        category.ifPresent(product::setCategory);
        return ResponseEntity.ok(productRepository.save(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product newProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(newProduct.getName());
            product.setPrice(newProduct.getPrice());
            product.setDescription(newProduct.getDescription());

            if (newProduct.getCategory() != null) {
                categoryRepository.findById(newProduct.getCategory().getId())
                        .ifPresent(product::setCategory);
            }

            return ResponseEntity.ok(productRepository.save(product));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
