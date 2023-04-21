package com.gc.hrworker.resources;

import com.gc.hrworker.entities.Worker;
import com.gc.hrworker.services.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

    private final Environment environment;
    private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);
    @Value("${test.config}")
    private String testConfig;

    private final WorkerService workerService;

    public WorkerResource(Environment environment, WorkerService workerService) {
        this.environment = environment;
        this.workerService = workerService;
    }
    @GetMapping(value = "/configs")
    public ResponseEntity<Void> getConfigs(){
        logger.info("CONFIG = " + testConfig);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll(){
        List<Worker> workerList = workerService.findAll();
        return ResponseEntity.ok().body(workerList);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id){
        logger.info("PORT="+ environment.getProperty("local.server.port"));

        Worker worker = workerService.findById(id);
        return ResponseEntity.ok().body(worker);
    }

    @PostMapping
    public ResponseEntity<Worker> createWorker(@RequestBody Worker worker){
        Worker createWorker = workerService.createWorker(worker);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteWorker(@PathVariable Long id){
        workerService.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Worker> updateWorker(@PathVariable Long id, @RequestBody Worker worker){
        Worker updateWorker = workerService.updateWorker(id, worker);
        return ResponseEntity.ok().body(updateWorker);
    }
}

