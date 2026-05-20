package dev.meetuco.homey_be.ProductCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductCategoryService {
  @Autowired
  private ProductCategoryRepository productCategoryRepository;

  protected List<ProductCategory> getAllProductCategories(){
    return productCategoryRepository.findAll();
  }
}
