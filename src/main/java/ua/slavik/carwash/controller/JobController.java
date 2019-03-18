package ua.slavik.carwash.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.dto.job.CreateJobDTO;
import ua.slavik.carwash.dto.job.JobDTO;
import ua.slavik.carwash.dto.job.UpdateJobDTO;
import ua.slavik.carwash.model.Job;
import ua.slavik.carwash.service.JobService;
import javax.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {
    private final ModelMapper modelMapper = new ModelMapper();
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createJob(@Valid @RequestBody CreateJobDTO jobDTO) {
        Job job = modelMapper.map(jobDTO, Job.class);
        Job savedJob = jobService.createJob(job);

        return new ResponseEntity<>(modelMapper.map(savedJob, JobDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{jobId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getJob(@PathVariable("jobId") Long id) {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelMapper.map(job, JobDTO.class), HttpStatus.OK);
    }

    @PutMapping(value = "/{jobId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateJob(@RequestBody UpdateJobDTO updateJobDTO, @PathVariable("jobId") Long id) {
        Job oldJob = modelMapper.map(updateJobDTO, Job.class);
        if (oldJob == null) {
            return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
        }
        Job updatedJob = jobService.updateJob(oldJob, id);

        return new ResponseEntity<>(modelMapper.map(updatedJob, JobDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{jobId}")
    public ResponseEntity deleteJob(@PathVariable("jobId") Long id) {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
        }
        jobService.deleteJob(id);

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
} 