package com.awbd.awbd.Controller;


import com.awbd.awbd.Service.DiscountServiceProxy;
import com.awbd.awbd.Service.ProductService;
import com.awbd.awbd.model.Discount;
import com.awbd.awbd.model.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    DiscountServiceProxy discountServiceProxy;

    @GetMapping("/product/title/{title}")
    Product findByTitle(@PathVariable String title){
        Product product = productService.findByTitle(title);
        Discount discount = discountServiceProxy.findDiscount();
        log.info(discount.getVersionId());
        product.setPrice(product.getPrice() - (discount.getValue()*product.getPrice())/100);
        return product;
    }


    @GetMapping(value = "/product/list", produces = {"application/hal+json"})
    public CollectionModel<Product> findAll() {

        List<Product> products = productService.findAll();
        for (final Product product : products) {
            Link selfLink = linkTo(methodOn(ProductController.class).getProduct(product.getId())).withSelfRel();
            product.add(selfLink);
            Link deleteLink = linkTo(methodOn(ProductController.class).deleteProduct(product.getId())).withRel("deleteProduct");
            product.add(deleteLink);
        }
        Link link = linkTo(methodOn(ProductController.class).findAll()).withSelfRel();
        CollectionModel<Product> result = CollectionModel.of(products, link);
        return result;
    }

    @PostMapping("/product")
    public ResponseEntity<Product> save( @RequestBody Product product){
        Product saveProduct = productService.save(product);
        URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{productId}").buildAndExpand(saveProduct.getId())
                .toUri();

        return ResponseEntity.created(locationUri).body(saveProduct);
    }


    @Operation(summary = "delete product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "product deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)})
    @DeleteMapping("/product/{productId}")
    public Product deleteProduct(@PathVariable Long productId) {

        Product product = productService.delete(productId);
        return product;
    }

    @GetMapping("/product/{productId}")
    @CircuitBreaker(name="discountById", fallbackMethod = "getProductFallback")
    public Product getProduct(@PathVariable Long productId) {

        System.out.println(productId);
        Product product = productService.findById(productId);
        Discount discount = discountServiceProxy.findDiscount();
        log.info("Version Discount" + discount.getVersionId());
        product.setPrice(product.getPrice() - (discount.getValue()*product.getPrice())/100);
        return product;
    }
    private Product getProductFallback(Long productId, Throwable throwable) {
        Product product = productService.findById(productId);
        log.info("Called getProductFallback() method" );
        return product;
    }
}
