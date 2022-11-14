package com.example.demo.listener;

import com.example.demo.domain.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class WriteListener implements ItemWriteListener<Employee> {

    @Override
    public void beforeWrite(List<? extends Employee> list) {
        // Do nothing..
    }

    @Override
    public void afterWrite(List<? extends Employee> list) {
        log.debug("AfterWrite: count = {}", list.size());
    }

    @Override
    public void onWriteError(Exception e, List<? extends Employee> list) {
        log.error("WriteError: errorMessage = {}", e.getMessage(), e);
    }
}
