package com.example.demo.config;

import com.example.demo.domain.model.Employee;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;

@EnableBatchProcessing
public class BaseConfig {

    @Autowired
    protected JobBuilderFactory jobBuilderFactory;

    @Autowired
    protected StepBuilderFactory stepBuilderFactory;

    /**
     * 性別の文字列を数値に変換するProcessor
     */
    @Autowired
    @Qualifier("GenderConvertProcessor")
    protected ItemProcessor<Employee, Employee> genderConvertProcessor;

    /**
     * ReadListener
     */
    @Autowired
    protected ItemReadListener<Employee> readListener;

    /**
     * ProcessListener
     */
    @Autowired
    protected ItemProcessListener<Employee, Employee> processListener;

    /**
     * WriteListener
     */
    @Autowired
    protected ItemWriteListener<Employee> writeListener;

    @Autowired
    protected SampleProperty property;

    /**
     * CSVファイルのReader
     */
    //@Bean
    @StepScope
    public FlatFileItemReader<Employee> csvReader() {
        // CSVのカラムに付ける名前
        String[] nameArray = new String[] {"id", "name", "age", "genderString"};

        // ファイルの読み込み
        return new FlatFileItemReaderBuilder<Employee>() // Builderの生成
                .name("employeeCsvReader") // 名前
                .resource(new ClassPathResource(property.getCsvPath())) // ファイルパス
                .linesToSkip(1) // スキップする行数
                .encoding(StandardCharsets.UTF_8.name()) // 文字コード
                .delimited() // DelimitedBuilderの取得
                    .names(nameArray) // ファイルのカラムに付ける名前
                    .fieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {
                        {
                            // ファイルの値を指定クラスにバインド
                            setTargetType(Employee.class);
                        }
                })
                .build(); // readerの生成
    }
}
