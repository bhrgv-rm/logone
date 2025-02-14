package com.logone.backend.Service;

import com.logone.backend.Model.JobApplication;
import com.logone.backend.Model.JobModel;
import com.logone.backend.Model.UserModel;
import com.logone.backend.Repository.JobApplicationRepository;
import com.logone.backend.Repository.JobRepository;
import com.logone.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobApplicationService {

  @Autowired
  private JobApplicationRepository jobApplicationRepository;

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private UserRepository userRepository;

  public List<JobApplication> getApplicationsByJob(UUID jobId) {
    return jobApplicationRepository.findByJobId(jobId);
  }

  public List<JobApplication> getApplicationsByUser(UUID userId) {
    return jobApplicationRepository.findByUserId(userId);
  }

  public JobApplication applyForJob(UUID jobId, UUID userId) {
    Optional<JobModel> jobOpt = jobRepository.findById(jobId);
    Optional<UserModel> userOpt = userRepository.findById(userId);

    if (jobOpt.isPresent() && userOpt.isPresent()) {
      JobApplication application = new JobApplication(jobOpt.get(), userOpt.get(), "Pending");
      return jobApplicationRepository.save(application);
    } else {
      throw new RuntimeException("Job or User not found.");
    }
  }

  public JobApplication updateApplicationStatus(UUID applicationId, String status) {
    Optional<JobApplication> applicationOpt = jobApplicationRepository.findById(applicationId);
    if (applicationOpt.isPresent()) {
      JobApplication application = applicationOpt.get();
      application.setStatus(status);
      return jobApplicationRepository.save(application);
    } else {
      throw new RuntimeException("Application not found.");
    }
  }
}
