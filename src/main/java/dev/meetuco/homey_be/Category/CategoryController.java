package dev.meetuco.homey_be.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
  public List<CategoryEntity> getCategories(){
    return categoryService.getAllCategories();
  }

  @PostMapping(consumes = "application/json")
  public CategoryEntity newCategoryEntity(@RequestBody CategoryEntity categoryEntity){
    return categoryService.addNewCategoryEntity(categoryEntity);
  }

  @PutMapping(consumes = "application/json")
  public ResponseEntity<?> updateCategoryEntity(@RequestBody CategoryEntity categoryEntity){
    return categoryService.updateCategoryEntity(categoryEntity);
  }

  @DeleteMapping
  public ResponseEntity<?> deleteCategoryEntity(@RequestBody CategoryEntity categoryEntity){
    return categoryService.deleteCategoryEntity(categoryEntity);
  }
}
