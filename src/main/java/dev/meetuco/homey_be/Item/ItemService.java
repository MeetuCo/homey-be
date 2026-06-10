package dev.meetuco.homey_be.Item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.meetuco.homey_be.Category.CategoryEntity;
import dev.meetuco.homey_be.Category.CategoryRepository;
import dev.meetuco.homey_be.Product.ProductEntity;
import dev.meetuco.homey_be.Product.ProductRepository;

@Service
public class ItemService {

  private final String itemNotFound = "Item id %s not found";

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  protected ResponseEntity<?> getAllItems(){
    List<ItemEntity> items = itemRepository.findAll();
    for (ItemEntity item : items){
      Long productEntityId = item.getProductEntityId();
      
      if (productRepository.existsById(productEntityId)){
        ProductEntity productEntity = productRepository.findById(productEntityId).get();
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(productEntity.getCategoryEntityId());
        if (categoryEntity.isEmpty()) {
          categoryEntity = categoryRepository.findById(1L);
        }
        productEntity.setCategoryEntity(categoryEntity);
        item.setProductEntity(Optional.of(productEntity));
      }
      else{
        itemRepository.delete(item);
      }
    }
    return new ResponseEntity<>(items, HttpStatus.OK);
  }

  protected ResponseEntity<?> addNewItem(ItemEntity itemEntity){
    itemRepository.save(itemEntity);
    return new ResponseEntity<>(itemEntity, HttpStatus.OK);
  }

  protected ResponseEntity<?> updateItem(Long id, ItemEntity itemEntity){
    try {
      ItemEntity existingItemEntity = itemRepository.findById(id).get();
      existingItemEntity.setCurrentAmount(itemEntity.getCurrentAmount());
      existingItemEntity.setExpiryDate(itemEntity.getExpiryDate());
      existingItemEntity.setProductEntityId(itemEntity.getProductEntityId());
      itemRepository.save(existingItemEntity);
      return new ResponseEntity<>(existingItemEntity, HttpStatus.OK);
    } catch (Exception e) {
      String itemNotFoundFormatted = itemNotFound.formatted(id);
      return new ResponseEntity<>(itemNotFoundFormatted, HttpStatus.NOT_FOUND);
    }
  }

  protected ResponseEntity<?> deleteItem(Long id){
    try {
      ItemEntity itemEntity = itemRepository.findById(id).get();
      itemRepository.deleteById(id);
      return new ResponseEntity<>(itemEntity, HttpStatus.OK);
    } catch (Exception e) {
      String itemNotFoundFormatted = itemNotFound.formatted(id);
      return new ResponseEntity<>(itemNotFoundFormatted, HttpStatus.NOT_FOUND);
    }
  }
}
