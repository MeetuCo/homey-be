package dev.meetuco.homey_be.Chore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chore")
public class ChoreEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String[] persons;

  // YYYY/MM/DD
  private String lastDoneDate;

  // In days
  @Column(name = "interval_days")
  private int interval;

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

  public String[] getPersons() {
    return persons;
  }

  public void setPersons(String[] persons) {
    this.persons = persons;
  }

  public String getLastDoneDate() {
    return lastDoneDate;
  }

  public void setLastDoneDate(String lastDoneDate) {
    this.lastDoneDate = lastDoneDate;
  }

  public int getInterval() {
    return interval;
  }

  public void setInterval(int interval) {
    this.interval = interval;
  }
}
