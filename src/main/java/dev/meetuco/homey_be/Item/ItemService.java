package dev.meetuco.homey_be.Item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.meetuco.homey_be.Product.ProductRepository;

@Service
public class ItemService {

  private final String nullProductId = "productId is required";
  private final String invalidProduct = "Product id %s is invalid";

  @Autowired
  ItemRepository itemEntityRepository;

  @Autowired
  ProductRepository productRepository;

  protected ResponseEntity<List<ItemEntity>> getAllItems(){
    List<ItemEntity> items = itemEntityRepository.findAll();
    return new ResponseEntity<>(items, HttpStatus.ACCEPTED);
  }

  protected ResponseEntity<?> addNewItem(ItemEntity itemEntity){
    Long productId = itemEntity.getProductId();
    
    if (productId == null) {
      return new ResponseEntity<>(nullProductId, HttpStatus.BAD_REQUEST);
    }

    if (productRepository.findById(productId).isEmpty()) {
      String invalidProductFormatted = invalidProduct.formatted(productId);
      return new ResponseEntity<>(invalidProductFormatted, HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(itemEntityRepository.save(itemEntity), HttpStatus.CREATED);
  }
} 
