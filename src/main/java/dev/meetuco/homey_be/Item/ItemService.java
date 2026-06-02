package dev.meetuco.homey_be.Item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.meetuco.homey_be.Product.ProductEntity;
import dev.meetuco.homey_be.Product.ProductRepository;
import dev.meetuco.homey_be.ProductCategory.CategoryEntity;
import dev.meetuco.homey_be.ProductCategory.CategoryRepository;

@Service
public class ItemService {

  private final String nullProductId = "productId is required";
  private final String invalidProduct = "Product id %s is invalid";

  @Autowired
  ItemRepository itemEntityRepository;

  @Autowired
  ProductRepository productEntityRepository;
  
  @Autowired
  CategoryRepository productCategoryRepository;

  protected ResponseEntity<List<ItemEntity>> getAllItems(){
    List<ItemEntity> items = itemEntityRepository.findAll();

    for (ItemEntity item : items){
      Long productId = item.getProductId();
      Optional<ProductEntity> product = productEntityRepository.findById(productId);
      product.ifPresent(item::setProductEntity);

      Long productCategoryEntityId = item.getProductEntity().getProductCategoryEntityId();
      Optional<CategoryEntity> productCategoryEntity = productCategoryRepository.findById(productCategoryEntityId);
      productCategoryEntity.ifPresent(product.get()::setProductCategoryEntity);
    }

    return new ResponseEntity<>(items, HttpStatus.ACCEPTED);
  }

  protected ResponseEntity<?> addNewItem(ItemEntity itemEntity){
    Long productId = itemEntity.getProductId();
    
    if (productId == null) {
      return new ResponseEntity<>(nullProductId, HttpStatus.BAD_REQUEST);
    }

    if (productEntityRepository.findById(productId).isEmpty()) {
      String invalidProductFormatted = invalidProduct.formatted(productId);
      return new ResponseEntity<>(invalidProductFormatted, HttpStatus.BAD_REQUEST);
    }

    itemEntityRepository.save(itemEntity);

    Long productEntityId = itemEntity.getProductId();
    Optional<ProductEntity> productEntity = productEntityRepository.findById(productEntityId);
    productEntity.ifPresent(itemEntity::setProductEntity);

    Long productCategoryEntityId = itemEntity.getProductEntity().getProductCategoryEntityId();
    Optional<CategoryEntity> productCategoryEntity = productCategoryRepository.findById(productCategoryEntityId);
    productCategoryEntity.ifPresent(productEntity.get()::setProductCategoryEntity);

    return new ResponseEntity<>(itemEntity, HttpStatus.CREATED);
  }
} 
