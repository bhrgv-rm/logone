package com.logone.backend.Controller;

import com.logone.backend.Model.RecruiterModel;
import com.logone.backend.Model.JobModel;
import com.logone.backend.Service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/recruiters")
public class RecruiterController {

  @Autowired
  private RecruiterService recruiterService;

  @GetMapping
  public List<RecruiterModel> getAllRecruiters() {
    return recruiterService.getAllRecruiters();
  }

  @GetMapping("/id/{id}")
  public Optional<RecruiterModel> getRecruiterById(@PathVariable UUID id) {
    return recruiterService.getRecruiterById(id);
  }

  @PostMapping
  public RecruiterModel createRecruiter(@RequestBody RecruiterModel recruiter) {
    return recruiterService.addRecruiter(recruiter);
  }

  @PutMapping("update/{id}")
  public RecruiterModel updateRecruiter(@PathVariable UUID id, @RequestBody RecruiterModel recruiter) {
    return recruiterService.updateRecruiter(id, recruiter);
  }

  @DeleteMapping("delete/{id}")
  public void deleteRecruiter(@PathVariable UUID id) {
    recruiterService.deleteRecruiter(id);
  }

  @PostMapping("/posting")
  public JobModel createPosting(@RequestBody JobModel job, @RequestHeader("recruiter-email") String recruiterEmail) {
    return recruiterService.addJobPosting(recruiterEmail, job);
  }

}
