package dev.meetuco.homey_be.ProductCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
  public CategoryEntity newCategoryEntity(@RequestBody CategoryEntity productCategoryEntity){
    return categoryService.addNewCategoryEntity(productCategoryEntity);
  }
}
