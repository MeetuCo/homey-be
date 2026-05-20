package dev.meetuco.homey_be.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  protected List<ProductEntity> getAllProducts(){
    return productRepository.findAll();
  }
}
