package com.gc.hrworker.services;

import com.gc.hrworker.entities.Worker;
import com.gc.hrworker.exceptions.WorkerNotFoundException;
import com.gc.hrworker.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {
    private WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public List<Worker> findAll(){
        List<Worker> workerList = workerRepository.findAll();
        return workerList;
    }
    public Worker findById(Long id){
        Worker worker = workerRepository.findById(id).orElseThrow(() -> new WorkerNotFoundException(id));
        return  worker;
    }

    public Worker createWorker(Worker worker) {
        Worker createdWorker = workerRepository.save(worker);
        return createdWorker;
    }

    public void deleteById(Long id) {
        workerRepository.deleteById(id);
    }

    public Worker updateWorker(Long id, Worker worker) {
        Worker updateWorker = findById(id);
        updateWorker.setName(worker.getName());
        updateWorker.setDailyIncome(worker.getDailyIncome());
        workerRepository.save(updateWorker);
        return updateWorker;
    }
}