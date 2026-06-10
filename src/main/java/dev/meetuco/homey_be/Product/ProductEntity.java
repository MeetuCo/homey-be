package dev.meetuco.homey_be.Product;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import dev.meetuco.homey_be.Category.CategoryEntity;
import jakarta.persistence.Column;
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
  
  @Column(nullable = true)
  @JsonProperty(index=3)
  private Integer targetAmount;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long categoryEntityId;
  
  @Transient
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Optional<CategoryEntity> categoryEntity;

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

  public Optional<Integer> getTargetAmount() {
    return Optional.ofNullable(targetAmount);
  }

  public void setTargetAmount(Integer targetAmount) {
    this.targetAmount = targetAmount;
  }

  public Long getCategoryEntityId() {
      return categoryEntityId;
  }

  public void setCategoryEntityId(Long categoryEntityId) {
      this.categoryEntityId = categoryEntityId;
  }

  public Optional<CategoryEntity> getCategoryEntity() {
    return categoryEntity;
  }

  public void setCategoryEntity(Optional<CategoryEntity> categoryEntity){
    this.categoryEntity = categoryEntity;
  }
}
