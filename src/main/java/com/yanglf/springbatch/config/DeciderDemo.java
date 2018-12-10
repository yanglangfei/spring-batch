package com.yanglf.springbatch.config;

import com.yanglf.springbatch.decider.MyJobExecutionDecider;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yanglf
 * @sine 2018.12.10
 * @descriptipon   决策器
 * @see
 */
@Configuration
@EnableBatchProcessing
public class DeciderDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Step deciderStep1(){
        return  stepBuilderFactory.get("deciderStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("deciderStep1");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }


    @Bean
    public Step deciderStep2(){
        return  stepBuilderFactory.get("deciderStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("deciderStep2");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }


    @Bean
    public Step deciderStep3(){
        return  stepBuilderFactory.get("deciderStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("deciderStep3");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }


    /**
     * @return   定义决策器
     */
    @Bean
    public JobExecutionDecider jobExecutionDecider(){
        return new MyJobExecutionDecider();
    }

    @Bean
    public Job deciderJob(){
        return jobBuilderFactory.get("deciderJob")
                .start(deciderStep1())
                .next(jobExecutionDecider())
                // 决策器 返回  event 执行 step2
                .from(jobExecutionDecider()).on("event").to(deciderStep2())
                // 决策器 返回  odd 执行 step3
                .from(jobExecutionDecider()).on("odd").to(deciderStep3())
                //  无论 step3  执行结果怎么样都返回 next(jobExecutionDecider())  这一步 执行  ，即执行完 132
                .from(deciderStep3()).on("*").to(jobExecutionDecider())
                .end()
                .build();

    }


}
