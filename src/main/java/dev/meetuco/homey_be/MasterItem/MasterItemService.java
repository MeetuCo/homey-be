package dev.meetuco.homey_be.MasterItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class MasterItemService {

  @Autowired
  private MasterItemRepository masterItemRepository;

  protected List<MasterItem> getAllMasterItems(){
    return masterItemRepository.findAll();
  }
}
