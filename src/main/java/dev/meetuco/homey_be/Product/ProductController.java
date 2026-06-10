package dev.meetuco.homey_be.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
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

  @PutMapping(consumes = "application/json")
  public ResponseEntity<?> updateProduct(@RequestBody ProductEntity productEntity){
    return productService.updateProduct(productEntity);
  }

  @DeleteMapping
  public ResponseEntity<?> deleteProduct(@RequestBody ProductEntity productEntity){
    return productService.deleteProduct(productEntity);
  }
}