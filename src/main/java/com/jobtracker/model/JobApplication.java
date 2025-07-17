package com.jobtracker.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "job_applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String companyName;
	private String position;
	private String location;

	@Enumerated(EnumType.STRING)
	private JobStatus status;

	private LocalDate appliedDate;

	@Column(length = 1000)
	private String notes;
}
