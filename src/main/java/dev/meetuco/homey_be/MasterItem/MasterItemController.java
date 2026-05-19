package dev.meetuco.homey_be.MasterItem;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/masteritem")
public class MasterItemController{

  @Autowired
  private MasterItemService masterItemService;

  @Autowired
  private MasterItemRepository masterItemRepository;

  @GetMapping(produces = "application/json")
  public List<MasterItem> getMasterItems(){
    return masterItemService.getAllMasterItems();
  }

  @PostMapping(consumes = "application/json")
  MasterItem newMasterItem(@RequestBody MasterItem masterItem){
    return masterItemRepository.save(masterItem);
  }
}