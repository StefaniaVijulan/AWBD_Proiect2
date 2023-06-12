package com.awbd.awbd.Service;

import com.awbd.awbd.Exceptions.ProductNotFound;
import com.awbd.awbd.Repository.ProductRepository;
import com.awbd.awbd.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product findByTitle(String title) {
        Product product = productRepository.findByTitle(title);
        return product;
    }

    @Override
    public Product save(Product product) {
        Product productSave = productRepository.save(product);
        return productSave;
    }

    @Override
    public List<Product> findAll(){
        List<Product> products = new LinkedList<>();
        productRepository.findAll().iterator().forEachRemaining(products::add);
        return products;
    }

    @Override
    public Product delete(Long id){
        Optional<Product> product = productRepository.findById(id);
        if (! product.isPresent())
            throw new ProductNotFound("Product " + id + " not found!");
        productRepository.delete(product.get());
        return product.get();
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (! productOptional.isPresent())
            throw new ProductNotFound("Product " + id + " not found!");
        return productOptional.get();
    }
}
