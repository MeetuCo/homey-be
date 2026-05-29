package dev.meetuco.homey_be.Product;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
public class ProductController{

  @Autowired
  private ProductService productService;


  @GetMapping(produces = "application/json")
  public List<ProductEntity> getProducts(){
    return productService.getAllProducts();
  }

  @PostMapping(consumes = "application/json")
  public ProductEntity newProduct(@RequestBody ProductEntity productEntity){
    return productService.addNewProduct(productEntity);
  }
}