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
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * @author yanglf
 * @sine 2018.12.10
 * @descriptipon
 * @see
 */
@Configuration
@EnableBatchProcessing
public class JobDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;


    @Autowired
    private StepBuilderFactory stepBuilderFactory;


   /* @Bean
    public Job job(){
        return jobBuilderFactory.get("job")
                *//*.start(demoStep1())
                .next(demoStep2())
                .next(demoStep3())*//*

                // 根据条件 决定是否执行下一个  step
                .start(demoStep1())
                .on("COMPLETED")
                *//*.fail() 使 step 失败  *//*
                *//*.stopAndRestart()   重启   一般用于测试 *//*
                .to(demoStep2())
                .from(demoStep2()).on("COMPLETED").to(demoStep3())
                .from(demoStep3()).end()
                .build();
    }*/


    @Bean
    @Primary
    public Step demoStep1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("this task is execute step1...");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step demoStep2() {
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("this task is execute step2...");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step demoStep3() {
        return stepBuilderFactory.get("step3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("this task is execute step3...");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

}
