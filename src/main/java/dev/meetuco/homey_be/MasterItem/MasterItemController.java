package dev.meetuco.homey_be.MasterItem;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterItemController{

  @GetMapping("/items")
  public String index(){
    return "Greetings";
  }
}