package com.example.demo.processor;

import com.example.demo.domain.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("GenderConvertProcessor")
@StepScope
@Slf4j
public class GenderConvertProcessor implements ItemProcessor<Employee, Employee> {

    /**
     * 性別の文字列を数値に変換する
     * @param employee
     * @return
     * @throws Exception
     */
    @Override
    public Employee process(Employee employee) throws Exception {
        try {
            employee.convertGenderStringToInt();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            // スキップ
            return null;
        }
        return employee;
    }
}
