package dev.meetuco.homey_be.Item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ItemService {

  @Autowired
  ItemRepository itemEntityRepository;

  protected List<ItemEntity> getAllItems(){
   return itemEntityRepository.findAll();
  }

  protected ItemEntity addNewItem(ItemEntity itemEntity){
    return itemEntityRepository.save(itemEntity);
  }
}
