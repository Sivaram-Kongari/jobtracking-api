package com.jobtracker.service;

import com.jobtracker.model.JobApplication;
import com.jobtracker.model.JobStatus;
import java.util.List;
import java.util.Optional;

public interface JobApplicationService {

	JobApplication createJob(JobApplication jobApplication);
	List<JobApplication> getAllJobs();
	Optional<JobApplication> getJobById(Long id);
	JobApplication updateJob(Long id, JobApplication updatedJob);
	void deleteJob(Long id);
	List<JobApplication> getJobsByStatus(JobStatus status);
}
