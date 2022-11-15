package com.example.demo.processor;

import com.example.demo.domain.model.Employee;
import com.example.demo.repository.EmployeeJdbcRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ExistsCheckProcessor")
@StepScope
@Slf4j
public class ExistsCheckProcessor implements ItemProcessor<Employee, Employee> {

    @Autowired
    private EmployeeJdbcRepository employeeRepository;

    /**
     * 従業員が存在するかチェックする
     *
     * @param employee
     * @return
     */
    @Override
    public Employee process(Employee employee) {
        boolean exists = employeeRepository.exits(employee.getId());
        if (exists) {
            log.info("Skip because it already exists: {}", employee);
            // Processorの返り値をnullにすると、そのデータはWriterには渡されない。
            return null;
        }
        return employee;
    }
}
