package com.awbd.awbd.Service;

import com.awbd.awbd.model.Product;

import java.util.List;

public interface ProductService {
    Product findByTitle(String title);
    Product save(Product subscription);
    List<Product> findAll();
    Product delete(Long id);
    Product findById(Long id);
}
