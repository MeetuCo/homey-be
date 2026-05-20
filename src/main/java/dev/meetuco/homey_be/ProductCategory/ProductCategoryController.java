package dev.meetuco.homey_be.ProductCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/category")
public class ProductCategoryController {
  
  @Autowired
  private ProductCategoryService productCategoryService;

  @GetMapping(produces = "application/json")
  public List<ProductCategoryEntity> getProductCategories(){
    return productCategoryService.getAllProductCategories();
  }

  @PostMapping(consumes = "application/json")
  public ProductCategoryEntity newProductCategoryEntity(@RequestBody ProductCategoryEntity productCategoryEntity){
    return productCategoryService.addNewProductCategoryEntity(productCategoryEntity);
  }
}
