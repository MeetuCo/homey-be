package dev.meetuco.homey_be.Chore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/chore")
public class ChoreController {

  @Autowired
  private ChoreService choreService;

  @GetMapping(produces = "application/json")
  public List<ChoreEntity> getChores(){
    return choreService.getAllChores();
  }

  @PostMapping(consumes = "application/json")
  public ChoreEntity newChore(@RequestBody ChoreEntity choreEntity){
    return choreService.addNewChore(choreEntity);
  }
}
