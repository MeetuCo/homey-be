package dev.meetuco.homey_be.Item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.meetuco.homey_be.Category.CategoryRepository;
import dev.meetuco.homey_be.Product.ProductEntity;
import dev.meetuco.homey_be.Product.ProductRepository;

@Service
public class ItemService {

  private final String invalidProduct = "Product id %s is invalid";

  @Autowired
  ItemRepository itemEntityRepository;

  @Autowired
  ProductRepository productEntityRepository;

  @Autowired
  CategoryRepository categoryRepository;

  protected ResponseEntity<List<ItemEntity>> getAllItems(){
    List<ItemEntity> items = itemEntityRepository.findAll();

    for (ItemEntity item : items){
      Long productId = item.getProductEntityId();

      Optional<ProductEntity> product = productEntityRepository.findById(productId);
      if (product.isEmpty()) {
        // TODO: return empty product or error of some sort
        item.setProductEntityId(0L);
      }
      else{
        ProductEntity productEntity = product.get();

        Long categoryId = productEntity.getCategoryEntityId();
        if (categoryId == null) {
          categoryId = 0L;
        }

        productEntity.setCategoryEntity(categoryRepository.findById(categoryId));
        item.setProductEntity(product);
      }
    }

    return new ResponseEntity<>(items, HttpStatus.OK);
  }

  protected ResponseEntity<?> addNewItem(ItemEntity itemEntity){
    Long productId = itemEntity.getProductEntityId();

    if (productExists(productId) == false){
      String invalidProductFormatted = invalidProduct.formatted(productId);
      return new ResponseEntity<>(invalidProductFormatted, HttpStatus.BAD_REQUEST);
    }

    itemEntityRepository.save(itemEntity);
    Optional<ProductEntity> productEntity = productEntityRepository.findById(itemEntity.getProductEntityId());
    itemEntity.setProductEntity(productEntity);
    return new ResponseEntity<>(itemEntity, HttpStatus.OK);
  }

  protected ResponseEntity<?> updateItem(ItemEntity itemEntity){
    try {
      itemEntityRepository.save(itemEntity);
      return new ResponseEntity<>(itemEntity, HttpStatus.OK);
    } catch (Exception e) {
      String invalidProductFormatted = invalidProduct.formatted(itemEntity.getProductEntityId());
      return new ResponseEntity<>(invalidProductFormatted, HttpStatus.NOT_FOUND);
    }
  }

  protected ResponseEntity<?> deleteItem(ItemEntity itemEntity){
    itemEntityRepository.delete(itemEntity);
    return new ResponseEntity<>(itemEntity, HttpStatus.OK);
  }

  private boolean productExists(Long id){
    Optional<ProductEntity> productEntity = productEntityRepository.findById(id);
    return productEntity.isPresent();
  }
}
