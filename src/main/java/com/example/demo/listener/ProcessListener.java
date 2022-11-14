package com.example.demo.listener;

import com.example.demo.domain.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProcessListener implements ItemProcessListener<Employee, Employee> {
    @Override
    public void beforeProcess(Employee employee) {
        // Do nothing..
    }

    @Override
    public void afterProcess(Employee employee, Employee employee2) {
        // Do nothing..
    }

    @Override
    public void onProcessError(Employee employee, Exception e) {
        log.error("ProcessError: employee = {}, errorMessage = {}", employee, e.getMessage(), e);
    }
}
