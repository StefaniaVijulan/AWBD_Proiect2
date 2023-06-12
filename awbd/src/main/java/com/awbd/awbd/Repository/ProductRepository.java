package com.awbd.awbd.Repository;

import com.awbd.awbd.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByTitle(String title);
}
