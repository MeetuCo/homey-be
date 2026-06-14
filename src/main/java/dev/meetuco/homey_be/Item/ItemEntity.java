package dev.meetuco.homey_be.Item;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import dev.meetuco.homey_be.Product.ProductEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "item")
public class ItemEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty(index = 0)
  private Long id;

  @JsonProperty(index = 1)
  private int currentAmount;

  @JsonProperty(index = 2)
  private String expiryDate;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long productEntityId;

  @Transient
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Optional<ProductEntity> productEntity;
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getCurrentAmount() {
    return currentAmount;
  }

  public void setCurrentAmount(int currentAmount) {
    this.currentAmount = currentAmount;
  }

  public String getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(String expiryDate) {
    this.expiryDate = expiryDate;
  }

  public Long getProductEntityId() {
    return productEntityId;
  }

  public void setProductEntityId(Long productId){
    this.productEntityId = productId;
  }

  public Optional<ProductEntity> getProductEntity() {
    return productEntity;
  }

  public void setProductEntity(Optional<ProductEntity> productEntity) {
    this.productEntity = productEntity;
  }
  
}
