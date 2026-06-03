package dev.meetuco.homey_be.Item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/items")
public class ItemController {
  @Autowired
  ItemService itemService;
  
  @GetMapping(produces = "application/json")
  public ResponseEntity<List<ItemEntity>> getItems(){
    return itemService.getAllItems();
  }

  @PostMapping(consumes = "application/json")
  public ResponseEntity<?> newItem(@RequestBody ItemEntity itemEntity){
    return itemService.addNewItem(itemEntity);
  }
}
