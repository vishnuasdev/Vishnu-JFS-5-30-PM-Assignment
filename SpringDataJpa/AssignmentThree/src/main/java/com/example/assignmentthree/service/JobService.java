package com.example.assignmentthree.service;

import com.example.assignmentthree.model.Job;
import com.example.assignmentthree.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> getHighSalaryJobs(double minSalary) {
        return jobRepository.findJobsBySalaryGreaterThan(minSalary);
    }

    public List<Job> getJobsByLocation(String location) {
        return jobRepository.findJobsByLocation(location);
    }

    public int updateSalary(Long id, double salary) {
        return jobRepository.updateJobSalary(id, salary);
    }

    public int deleteByCompany(String company) {
        return jobRepository.deleteJobsByCompany(company);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}