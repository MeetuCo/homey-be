package dev.meetuco.homey_be.ProductCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/category")
public class ProductCategoryController {
  
  @Autowired
  private ProductCategoryService productCategoryService;

  @GetMapping(produces = "application/json")
  public List<ProductCategory> getProductCategories(){
    return productCategoryService.getAllProductCategories();
  }
}
