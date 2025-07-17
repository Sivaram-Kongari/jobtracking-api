package com.jobtracker.repository;

import com.jobtracker.model.JobApplication;
import com.jobtracker.model.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

	List<JobApplication> findByStatus(JobStatus status);
	List<JobApplication> findByCompanyNameContainingIgnoreCase(String keyword);
}
