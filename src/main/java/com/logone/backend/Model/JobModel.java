package com.logone.backend.Model;

import jakarta.persistence.*;
import java.util.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
public class JobModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String jobTitle;
  private String description;
  private String location;

  @ManyToOne
  @JoinColumn(name = "recruiter_id", nullable = false)
  private RecruiterModel recruiter; // The recruiter who created the job

  @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<JobApplication> applications = new ArrayList<>();

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

  public RecruiterModel getRecruiter() {
    return recruiter;
  }

  public void setRecruiter(RecruiterModel recruiter) {
    this.recruiter = recruiter;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public List<JobApplication> getApplicants() {
    return applications;
  }

  public void setApplications(List<JobApplication> applications) {
    this.applications = applications;
  }

}