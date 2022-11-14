package com.example.demo.listener;

import com.example.demo.domain.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReadListener implements ItemReadListener<Employee> {
    @Override
    public void beforeRead() {
        // Do nothing..
    }

    @Override
    public void afterRead(Employee employee) {
        log.debug("After Read: {}", employee);
    }

    @Override
    public void onReadError(Exception e) {
        log.error("ReadError: errorMessage = {}", e.getMessage(), e);
    }
}
