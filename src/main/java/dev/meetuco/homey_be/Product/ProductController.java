package dev.meetuco.homey_be.Product;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/masteritem")
public class ProductController{

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductRepository masterItemRepository;

  @GetMapping(produces = "application/json")
  public List<ProductEntity> getProducts(){
    return productService.getAllProducts();
  }

  @PostMapping(consumes = "application/json")
  ProductEntity newProduct(@RequestBody ProductEntity masterItem){
    return masterItemRepository.save(masterItem);
  }
}