package dev.meetuco.homey_be.ProductCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  protected List<CategoryEntity> getAllCategories(){
    return categoryRepository.findAll();
  }

  protected CategoryEntity addNewCategoryEntity(CategoryEntity productCategoryEntity){
    return categoryRepository.save(productCategoryEntity);
  }
}
