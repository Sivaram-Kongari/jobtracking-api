package com.jobtracker.controller;

import com.jobtracker.model.JobApplication;
import com.jobtracker.model.JobStatus;
import com.jobtracker.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobApplicationController {

	private final JobApplicationService jobService;

	@Autowired
	public JobApplicationController(JobApplicationService jobService) {

		this.jobService = jobService;
	}

	// Create a new job application
	@PostMapping
	public JobApplication createJob(@RequestBody JobApplication jobApplication) {

		return jobService.createJob(jobApplication);
	}

	// Get all job applications
	@GetMapping
	public List<JobApplication> getAllJobs() {

		return jobService.getAllJobs();
	}

	// Get job by ID
	@GetMapping("/{id}")
	public JobApplication getJobById(@PathVariable Long id) {

		return jobService.getJobById(id)
				.orElseThrow(() -> new RuntimeException("Job not found with id " + id));
	}

	// Update a job application
	@PutMapping("/{id}")
	public JobApplication updateJob(@PathVariable Long id, @RequestBody JobApplication jobApplication) {

		return jobService.updateJob(id, jobApplication);
	}

	// Delete a job application
	@DeleteMapping("/{id}")
	public void deleteJob(@PathVariable Long id) {

		jobService.deleteJob(id);
	}

	// Get jobs by status
	@GetMapping("/status/{status}")
	public List<JobApplication> getJobsByStatus(@PathVariable JobStatus status) {

		return jobService.getJobsByStatus(status);
	}
}
