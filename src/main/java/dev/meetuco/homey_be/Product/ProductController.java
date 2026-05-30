package dev.meetuco.homey_be.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<?> getProducts(){
    return productService.getAllProducts();
  }

  @PostMapping(consumes = "application/json")
  public ResponseEntity<?> newProduct(@RequestBody ProductEntity productEntity){
    return productService.addNewProduct(productEntity);
  }
}