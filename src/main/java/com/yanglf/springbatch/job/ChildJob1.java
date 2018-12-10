package com.yanglf.springbatch.job;

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
public class ChildJob1 {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step childJob1Step1(){
        return stepBuilderFactory.get("childStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("childJob1Step1");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }


    @Bean
    public Job childJob1(){
        return jobBuilderFactory.get("childJob1")
                .start(childJob1Step1())
                .build();
    }
}
