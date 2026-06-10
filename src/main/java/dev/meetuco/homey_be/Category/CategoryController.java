package dev.meetuco.homey_be.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
  
  @Autowired
  private CategoryService categoryService;

  @GetMapping(produces = "application/json")
  public ResponseEntity<?> getCategories(){
    return categoryService.getAllCategories();
  }

  @PostMapping(consumes = "application/json")
  public ResponseEntity<?> newCategoryEntity(@RequestBody CategoryEntity categoryEntity){
    return categoryService.addNewCategoryEntity(categoryEntity);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateCategoryEntity(@PathVariable("id") Long id, @RequestBody CategoryEntity categoryEntity){
    return categoryService.updateCategoryEntity(id, categoryEntity);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCategoryEntity(@PathVariable("id") Long id){
    return categoryService.deleteCategoryEntity(id);
  }
}
