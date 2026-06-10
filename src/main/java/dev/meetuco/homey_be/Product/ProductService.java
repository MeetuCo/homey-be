package dev.meetuco.homey_be.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.meetuco.homey_be.Category.CategoryEntity;
import dev.meetuco.homey_be.Category.CategoryRepository;

@Service
public class ProductService {

  private final String productNotFound = "Product id %s not found";

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  protected ResponseEntity<?> getAllProducts(){
    List<ProductEntity> products = productRepository.findAll();

    for (ProductEntity product : products){
      Long categoryId = product.getCategoryEntityId();

      if (categoryExists(categoryId)){
        product.setCategoryEntity(categoryRepository.findById(categoryId));
      }
      else{
        product.setCategoryEntity(categoryRepository.findById(1L));
        product.setCategoryEntityId(1L);
        productRepository.save(product);
      }
    }

    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  protected ResponseEntity<?> addNewProduct(ProductEntity productEntity){
    Long id = Optional.ofNullable(productEntity.getCategoryEntityId()).orElse(0L);

    if (categoryExists(id) == false){
      productEntity.setCategoryEntityId(0L);
    }

    productRepository.save(productEntity);
    Optional<CategoryEntity> categoryEntity = categoryRepository.findById(productEntity.getCategoryEntityId());
    productEntity.setCategoryEntity(categoryEntity);
    return new ResponseEntity<>(productEntity, HttpStatus.OK);
  }

  protected ResponseEntity<?> updateProduct(Long id, ProductEntity productEntity){
    try {
      ProductEntity existingProductEntity = productRepository.findById(id).get();
      existingProductEntity.setCategoryEntityId(productEntity.getCategoryEntityId());
      existingProductEntity.setName(productEntity.getName());
      existingProductEntity.setTargetAmount(productEntity.getTargetAmount());
      productRepository.save(existingProductEntity);
      return new ResponseEntity<>(existingProductEntity, HttpStatus.OK);
    } catch (Exception e) {
      String productNotFoundFormatted = productNotFound.formatted(id);
      return new ResponseEntity<>(productNotFoundFormatted, HttpStatus.NOT_FOUND);
    }
  }

  protected ResponseEntity<?> deleteProduct(Long id){
    try {
      ProductEntity productEntity = productRepository.findById(id).get();
      productRepository.deleteById(id);
      return new ResponseEntity<>(productEntity, HttpStatus.OK);
        
    } catch (Exception e) {
      String productNotFoundFormatted = productNotFound.formatted(id);
      return new ResponseEntity<>(productNotFoundFormatted, HttpStatus.NOT_FOUND);
    }
  }
 
  private boolean categoryExists(Long id){
    Optional<CategoryEntity> category = categoryRepository.findById(id);
    return category.isPresent();
  }
}
