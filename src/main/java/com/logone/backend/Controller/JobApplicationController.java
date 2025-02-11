package com.logone.backend.Controller;

import com.logone.backend.Model.JobApplication;
import com.logone.backend.Service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/applications")
public class JobApplicationController {

  @Autowired
  private JobApplicationService jobApplicationService;

  @GetMapping("/job/{jobId}")
  public List<JobApplication> getApplicationsByJob(@PathVariable UUID jobId) {
    return jobApplicationService.getApplicationsByJob(jobId);
  }

  @GetMapping("/user/{userId}")
  public List<JobApplication> getApplicationsByUser(@PathVariable UUID userId) {
    return jobApplicationService.getApplicationsByUser(userId);
  }

  @PostMapping("/apply/{jobId}/{userId}")
  public JobApplication applyForJob(@PathVariable UUID jobId, @PathVariable UUID userId) {
    return jobApplicationService.applyForJob(jobId, userId);
  }

  @PutMapping("/update-status/{applicationId}")
  public JobApplication updateApplicationStatus(@PathVariable UUID applicationId, @RequestParam String status) {
    return jobApplicationService.updateApplicationStatus(applicationId, status);
  }
}
