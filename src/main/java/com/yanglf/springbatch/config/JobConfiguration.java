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
 * @sine 2018.12.10
 * @descriptipon
 * @see
 */
@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    /**
     * 注入创建对象任务的对象
     */
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    //
    /**
     * 注入创建 step 对象的 对象      任务的执行有step组成
     */
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * @return  创建任务
     */
   /* @Bean
    public Job createJob(){
        return jobBuilderFactory.get("createJob")
                .start(step1())
                .build();
    }*/

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("this step is running...");
                        // 只有这个 step 正常结束了  下一个 step 才会执行
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }


}
