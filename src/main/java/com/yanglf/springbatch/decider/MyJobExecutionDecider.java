package com.yanglf.springbatch.decider;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * @author yanglf
 * @sine 2018.12.10
 * @descriptipon  自定义决策器
 * @see
 */
public class MyJobExecutionDecider implements JobExecutionDecider {

    int count ;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
         count++;
         if(count%2==0){
             return new FlowExecutionStatus("event");
         }else {
             return new FlowExecutionStatus("odd");
         }
    }
}
