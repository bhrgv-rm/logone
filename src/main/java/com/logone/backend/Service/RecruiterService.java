package com.logone.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.logone.backend.Repository.RecruiterRepository;
import com.logone.backend.Model.RecruiterModel;
import java.util.*;

@Service
public class RecruiterService {

  @Autowired
  private RecruiterRepository recruiterRepository;

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

  public void deleteRecruiter(UUID id) {
    recruiterRepository.deleteById(id);
  }
}
