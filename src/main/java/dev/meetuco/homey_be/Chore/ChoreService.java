package dev.meetuco.homey_be.Chore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChoreService {

  @Autowired
  private ChoreRepository choreRepository;

  protected List<ChoreEntity> getAllChores(){
    return choreRepository.findAll();
  }

  protected ChoreEntity addNewChore(ChoreEntity choreEntity){
    return choreRepository.save(choreEntity);
  }
}
