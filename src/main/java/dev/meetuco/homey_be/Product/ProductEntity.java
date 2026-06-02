package dev.meetuco.homey_be.Product;

import com.fasterxml.jackson.annotation.JsonProperty;

import dev.meetuco.homey_be.ProductCategory.CategoryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "product")

public class ProductEntity {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @JsonProperty(index=0)
  private Long id;

  @JsonProperty(index=1)
  private String name;
  
  @JsonProperty(index=3)
  private int targetAmount;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long productCategoryEntityId;
  
  @Transient
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private CategoryEntity productCategoryEntity;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getTargetAmount() {
    return targetAmount;
  }

  public void setTargetAmount(int targetAmount) {
    this.targetAmount = targetAmount;
  }

  public Long getProductCategoryEntityId() {
      return productCategoryEntityId;
  }

  public void setProductCategoryEntityId(Long ProductCategoryEntityId) {
      this.productCategoryEntityId = ProductCategoryEntityId;
  }

  public CategoryEntity getProductCategoryEntity() {
    return productCategoryEntity;
  }

  public void setProductCategoryEntity(CategoryEntity productCategoryEntity) {
    this.productCategoryEntity = productCategoryEntity;
  }

}
