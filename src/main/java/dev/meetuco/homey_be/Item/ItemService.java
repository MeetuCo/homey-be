package dev.meetuco.homey_be.Item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  private final String itemNotFound = "Item id %s not found";

  @Autowired
  private ItemRepository itemRepository;

  protected ResponseEntity<?> getAllItems(){
    List<ItemEntity> items = itemRepository.findAll();
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
