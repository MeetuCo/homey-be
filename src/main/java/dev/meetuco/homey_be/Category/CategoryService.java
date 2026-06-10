package dev.meetuco.homey_be.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  private static final String defaultCategoryName = "Default";
  private final String categoryNotFound = "Category id %s not found";

  @Autowired
  private CategoryRepository categoryRepository;

  protected ResponseEntity<?> getAllCategories(){
    List<CategoryEntity> categories = categoryRepository.findAll();
    return new ResponseEntity<>(categories, HttpStatus.OK);
  }

  protected ResponseEntity<?> addNewCategoryEntity(CategoryEntity categoryEntity){
    categoryRepository.save(categoryEntity);
    return new ResponseEntity<>(categoryEntity, HttpStatus.OK);
  }

  protected ResponseEntity<?> updateCategoryEntity(Long id, CategoryEntity categoryEntity){
    if (categoryEntity.getName() != null && defaultCategoryName.equalsIgnoreCase(categoryEntity.getName())){
      return new ResponseEntity<>("Cannot update DEFAULT category", HttpStatus.FORBIDDEN);
    }

    try {
      CategoryEntity existingCategoryEntity = categoryRepository.findById(id).get();
      existingCategoryEntity.setName(categoryEntity.getName());
      existingCategoryEntity.setColor(categoryEntity.getColor());
      categoryRepository.save(existingCategoryEntity);
      return new ResponseEntity<>(existingCategoryEntity, HttpStatus.OK);
    } catch (Exception e) {
      String categoryNotFoundFormatted = categoryNotFound.formatted(id);
      return new ResponseEntity<>(categoryNotFoundFormatted, HttpStatus.NOT_FOUND);
    }
  }

  protected ResponseEntity<?> deleteCategoryEntity(Long id){
    try {
      CategoryEntity categoryEntity = categoryRepository.findById(id).get();

      if (categoryEntity.getName() != null && defaultCategoryName.equalsIgnoreCase(categoryEntity.getName())){
        return new ResponseEntity<>("Cannot delete DEFAULT category", HttpStatus.FORBIDDEN);
      }

      categoryRepository.deleteById(id);
      return new ResponseEntity<>(categoryEntity, HttpStatus.OK);
    } catch (Exception e) {
      String categoryNotFoundFormatted = categoryNotFound.formatted(id);
      return new ResponseEntity<>(categoryNotFoundFormatted, HttpStatus.NOT_FOUND);
    }
  }
}
