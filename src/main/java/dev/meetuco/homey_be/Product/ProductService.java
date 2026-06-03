package dev.meetuco.homey_be.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.meetuco.homey_be.ProductCategory.CategoryEntity;
import dev.meetuco.homey_be.ProductCategory.CategoryRepository;

@Service
public class ProductService {

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
        product.setCategoryEntity(categoryRepository.findById(0L));
        product.setCategoryEntityId(0L);
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

  private boolean categoryExists(Long id){
    Optional<CategoryEntity> category = categoryRepository.findById(id);
    return category.isPresent();
  }
}
