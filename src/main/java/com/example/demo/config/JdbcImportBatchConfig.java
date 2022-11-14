package com.example.demo.config;

import com.example.demo.domain.model.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public abstract class JdbcImportBatchConfig extends BaseConfig {

    /**
     * DataSource
     */
    private DataSource dataSource;

    private static final String INSERT_EMPLOYEE_SQL =
            "INSERT INTO employee (id, name, age, gender)"
            + "VALUES(:id, :name, :age, :gender)";

    /**
     * Writer
     */
    public JdbcBatchItemWriter<Employee> jdbcWriter() {
        // Provider生成
        BeanPropertyItemSqlParameterSourceProvider<Employee> provider =
                new BeanPropertyItemSqlParameterSourceProvider<>();

        // 設定
        return new JdbcBatchItemWriterBuilder<Employee>()
                .itemSqlParameterSourceProvider(provider)
                .sql(INSERT_EMPLOYEE_SQL)
                .dataSource(this.dataSource)
                .build();
    }

    /**
     * Stepの生成
     */
//    @Bean
    public Step csvImportJdbcStep() {
        return this.stepBuilderFactory.get("CsvImportJdbcStep")
                .<Employee, Employee>chunk(10)
                .reader(csvReader()).listener(this.readListener)
                .processor(genderConvertProcessor).listener(this.processListener)
                .writer(jdbcWriter()).listener(this.writeListener)
                .build();
    }

    /**
     * Jobの生成
     */
    @Bean("JdbcJob")
    public Job csvImportJdbcJob() {
        return this.jobBuilderFactory.get("CsvImportJdbcJob")
                .incrementer(new RunIdIncrementer())
                .start(csvImportJdbcStep())
                .build();
    }
}
