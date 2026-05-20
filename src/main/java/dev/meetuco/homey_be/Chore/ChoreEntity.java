package dev.meetuco.homey_be.Chore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Chore")
public class ChoreEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String[] persons;

  // YYYY/MM/DD
  private String lastDoneDate;

  // In days
  private int interval;
}
