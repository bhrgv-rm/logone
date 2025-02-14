package com.logone.backend.Repository;

import com.logone.backend.Model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, UUID> {
  List<JobApplication> findByJobId(UUID jobId);

  List<JobApplication> findByUserId(UUID userId);
}
