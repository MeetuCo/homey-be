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
  private CategoryRepository productCategoryRepository;

  protected ResponseEntity<?> getAllProducts(){
    List<ProductEntity> products = productRepository.findAll();

    for (ProductEntity product : products){
      Long productCategoryEntityId = product.getProductCategoryEntityId();
      Optional<CategoryEntity> productCategoryEntity = productCategoryRepository.findById(productCategoryEntityId);
      productCategoryEntity.ifPresent(product::setProductCategoryEntity);
    }

    return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
  }

  protected ResponseEntity<?> addNewProduct(ProductEntity productEntity){
    if (productEntity.getProductCategoryEntityId() == null){
      productEntity.setProductCategoryEntityId(0L);
    }

    productRepository.save(productEntity);

    Long productCategoryEntityId = productEntity.getProductCategoryEntityId();
    Optional<CategoryEntity> productCategoryEntity = productCategoryRepository.findById(productCategoryEntityId);
    productCategoryEntity.ifPresent(productEntity::setProductCategoryEntity);

    return new ResponseEntity<>(productEntity, HttpStatus.CREATED);
  }
}
