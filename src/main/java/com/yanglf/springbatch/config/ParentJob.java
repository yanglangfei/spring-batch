package com.yanglf.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yanglf
 * @sine 2018.12.11
 * @descriptipon   job 嵌套
 * @see
 */
@Configuration
@EnableBatchProcessing
public class ParentJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job parentJob(){
        return jobBuilderFactory.get("parentJob")
                .start(childJob1())
                .next(childJob2())
                .build();
    }

    private Step childJob2() {
        return null;
    }

    private Step childJob1() {
        return null;
    }
}
