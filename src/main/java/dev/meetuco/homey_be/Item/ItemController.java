package dev.meetuco.homey_be.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/items")
public class ItemController {
  @Autowired
  private ItemService itemService;
  
  @GetMapping(produces = "application/json")
  public ResponseEntity<?> getItems(@RequestParam(required = false) String expiring){
    if (expiring == null){
      return itemService.getAllItems();
    }
    else{
      return itemService.getAllExpiringItems(expiring);
    }
  }

  @PostMapping(consumes = "application/json")
  public ResponseEntity<?> newItem(@RequestBody ItemEntity itemEntity){
    return itemService.addNewItem(itemEntity);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateItem(@PathVariable("id") Long id, @RequestBody ItemEntity itemEntity){
    return itemService.updateItem(id, itemEntity);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteItem(@PathVariable("id") Long id){
    return itemService.deleteItem(id);
  }
}
