package dev.meetuco.homey_be.Chore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ChoreService {

  @Autowired
  ChoreRepository choreRepository;

  protected List<ChoreEntity> getAllChores(){
    return choreRepository.findAll();
  }

  protected ChoreEntity addNewChore(ChoreEntity choreEntity){
    return choreRepository.save(choreEntity);
  }
}
