package com.logone.backend.Repository;

import com.logone.backend.Model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<JobModel, UUID> {
  List<JobModel> findByRecruiterId(UUID recruiterId);
}
