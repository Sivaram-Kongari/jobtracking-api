package com.jobtracker.service;

import com.jobtracker.model.JobApplication;
import com.jobtracker.model.JobStatus;
import com.jobtracker.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

	private final JobApplicationRepository jobRepo;

	@Autowired
	public JobApplicationServiceImpl(JobApplicationRepository jobRepo) {

		this.jobRepo = jobRepo;
	}

	@Override
	public JobApplication createJob(JobApplication jobApplication) {

		return jobRepo.save(jobApplication);
	}

	@Override
	public List<JobApplication> getAllJobs() {

		return jobRepo.findAll();
	}

	@Override
	public Optional<JobApplication> getJobById(Long id) {

		return jobRepo.findById(id);
	}

	@Override
	public JobApplication updateJob(Long id, JobApplication updatedJob) {

		return jobRepo.findById(id)
				.map(existing -> {
					existing.setCompanyName(updatedJob.getCompanyName());
					existing.setPosition(updatedJob.getPosition());
					existing.setLocation(updatedJob.getLocation());
					existing.setStatus(updatedJob.getStatus());
					existing.setAppliedDate(updatedJob.getAppliedDate());
					existing.setNotes(updatedJob.getNotes());
					return jobRepo.save(existing);
				}).orElseThrow(() -> new RuntimeException("Job not found with id " + id));
	}

	@Override
	public void deleteJob(Long id) {

		jobRepo.deleteById(id);
	}

	@Override
	public List<JobApplication> getJobsByStatus(JobStatus status) {

		return jobRepo.findByStatus(status);	
	}
}
