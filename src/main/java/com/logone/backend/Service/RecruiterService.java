package com.logone.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logone.backend.Repository.JobRepository;
import com.logone.backend.Repository.RecruiterRepository;
import com.logone.backend.Model.JobModel;
import com.logone.backend.Model.RecruiterModel;
import java.util.*;

@Service
public class RecruiterService {

  @Autowired
  private RecruiterRepository recruiterRepository;
  @Autowired
  private JobRepository jobRepository;

  public List<RecruiterModel> getAllRecruiters() {
    return recruiterRepository.findAll();
  }

  public Optional<RecruiterModel> getRecruiterById(UUID id) {
    return recruiterRepository.findById(id);
  }

  public RecruiterModel addRecruiter(RecruiterModel recruiterModel) {
    return recruiterRepository.save(recruiterModel);
  }

  public RecruiterModel updateRecruiter(UUID id, RecruiterModel recruiterDetails) {
    Optional<RecruiterModel> recruiterOptional = recruiterRepository.findById(id);
    if (recruiterOptional.isPresent()) {
      RecruiterModel recruiter = recruiterOptional.get();
      recruiter.setName(recruiterDetails.getName());
      recruiter.setEmail(recruiterDetails.getEmail());
      return recruiterRepository.save(recruiter);
    } else {
      throw new RuntimeException("Recruiter not found with id " + id);
    }
  }

  public JobModel addJobPosting(String recruiterEmail, JobModel job) {
    RecruiterModel recruiter = recruiterRepository.findByEmail(recruiterEmail);

    if (recruiter == null) {
      throw new RuntimeException("Recruiter not found");
    }

    job.setRecruiter(recruiter); // Assign recruiter to job
    return jobRepository.save(job);
  }

  public void deleteRecruiter(UUID id) {
    recruiterRepository.deleteById(id);
  }
}
