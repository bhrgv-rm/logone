package com.logone.backend.Model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "job_applications")
public class JobApplication {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "job_id", nullable = false)
  private JobModel job;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserModel user;

  @Column(nullable = false)
  private String status; // e.g., "Pending", "Accepted", "Rejected"

  // Constructors
  public JobApplication() {
  }

  public JobApplication(JobModel job, UserModel user, String status) {
    this.job = job;
    this.user = user;
    this.status = status;
  }

  // Getters and Setters
  public UUID getId() {
    return id;
  }

  public JobModel getJob() {
    return job;
  }

  public void setJob(JobModel job) {
    this.job = job;
  }

  public UserModel getUser() {
    return user;
  }

  public void setUser(UserModel user) {
    this.user = user;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
