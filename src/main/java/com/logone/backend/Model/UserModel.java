package com.logone.backend.Model;

import jakarta.persistence.*;
import java.util.UUID;

import javax.validation.constraints.Email;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Email
  @Column(unique = true)
  private String email;

  private String name;

  @Column(name = "password")
  private String password;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime updatedAt;

  @PrePersist
  public void prePersist() {
    createdAt = updatedAt = LocalDateTime.now();
    if (id == null) {
      id = UUID.randomUUID();
    }
  }

  @PreUpdate
  public void preUpdate() {
    updatedAt = LocalDateTime.now();
  }

  // Getters and Setters
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
