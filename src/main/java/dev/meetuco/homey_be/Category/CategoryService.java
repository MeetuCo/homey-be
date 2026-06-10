package dev.meetuco.homey_be.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  protected List<CategoryEntity> getAllCategories(){
    return categoryRepository.findAll();
  }

  protected CategoryEntity addNewCategoryEntity(CategoryEntity categoryEntity){
    return categoryRepository.save(categoryEntity);
  }

  protected ResponseEntity<?> updateCategoryEntity(CategoryEntity categoryEntity){
    try {
      categoryRepository.save(categoryEntity);
      return new ResponseEntity<>(categoryEntity, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(categoryEntity, HttpStatus.NOT_FOUND);
    }
  }

  protected ResponseEntity<?> deleteCategoryEntity(CategoryEntity categoryEntity){
    categoryRepository.delete(categoryEntity);
    return new ResponseEntity<>(categoryEntity, HttpStatus.OK);
  }
}
