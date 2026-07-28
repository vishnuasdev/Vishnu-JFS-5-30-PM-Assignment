package com.example.assignmentthree.controller;

import com.example.assignmentthree.model.Job;
import com.example.assignmentthree.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        return ResponseEntity.ok(jobService.saveJob(job));
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/high-salary")
    public ResponseEntity<List<Job>> getJobsWithHighSalary(@RequestParam(defaultValue = "50000") double minSalary) {
        return ResponseEntity.ok(jobService.getHighSalaryJobs(minSalary));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Job>> getJobsByLocation(@PathVariable String location) {
        return ResponseEntity.ok(jobService.getJobsByLocation(location));
    }

    @PutMapping("/{id}/salary")
    public ResponseEntity<String> updateSalary(@PathVariable Long id, @RequestParam double salary) {
        int updatedRows = jobService.updateSalary(id, salary);
        if (updatedRows > 0) {
            return ResponseEntity.ok("Job salary updated successfully.");
        }
        return ResponseEntity.badRequest().body("Job ID not found.");
    }

    @DeleteMapping("/company/{company}")
    public ResponseEntity<String> deleteByCompany(@PathVariable String company) {
        int deletedRows = jobService.deleteByCompany(company);
        return ResponseEntity.ok("Deleted " + deletedRows + " job(s) from " + company);
    }
}