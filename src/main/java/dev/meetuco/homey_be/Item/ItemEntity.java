package dev.meetuco.homey_be.Item;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class ItemEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty(index = 0)
  private Long id;

  @JsonProperty(index = 1)
  private int currentAmount;

  // YYYY/MM/DD
  @JsonProperty(index = 2)
  private String expiryDate;

  private Long productId;
  
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

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId){
    this.productId = productId;
  }
  
}
