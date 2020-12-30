package com.ruqu.trigger.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池工具类
 * @Author Reny
 * @Package cn.net.ydesign.ydmis.config
 * @Date 2018-12-09 9:08
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncTaskConfig implements AsyncConfigurer {

    @Value("${spring.taskExecutor.corePoolSize}")
    Integer corePoolSize;
    @Value("${spring.taskExecutor.maxPoolSize}")
    Integer maxPoolSize;
    @Value("${spring.taskExecutor.queueCapacity}")
    Integer queueCapacity;
    @Value("${spring.taskExecutor.threadNamePrefix}")
    String threadNamePrefix;
    @Value("${spring.taskExecutor.allowCoreThreadTimeout}")
    boolean allowCoreThreadTimeout;
    @Value("${spring.taskExecutor.keepAliveTime}")
    Integer keepAliveTime;
    @Value("${spring.taskExecutor.waitForTasksToCompleteOnShutdown}")
    boolean waitForTasksToCompleteOnShutdown;
    @Value("${spring.taskExecutor.AwaitTerminationSeconds}")
    Integer awaitTerminationSeconds;

    @Override
    public Executor getAsyncExecutor() { // 配置类实现AsyncConfigurer接口并重写 getAsyncExecutor 方法,并返回一个 ThreadPoolTaskExecutor,这样我们就获得了一个线程池 taskExecutor
        log.info("开启线程池");
        ThreadPoolTaskExecutor taskExecutor = new VisiableThreadPoolTaskExecutor();
        //设置allowCoreThreadTimeout=true（默认false）时，核心线程会超时关闭
        taskExecutor.setAllowCoreThreadTimeOut(allowCoreThreadTimeout);
        //配置核心线程数
        taskExecutor.setCorePoolSize(corePoolSize);
        //配置最大线程数
        taskExecutor.setMaxPoolSize(maxPoolSize);
        //配置队列大小
        taskExecutor.setQueueCapacity(queueCapacity);
        //配置线程池中的线程的名称前缀
        taskExecutor.setThreadNamePrefix(threadNamePrefix);
        //线程空闲时间
        taskExecutor.setKeepAliveSeconds(keepAliveTime);
        //设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        taskExecutor.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);
        // 设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        taskExecutor.setAwaitTerminationSeconds(awaitTerminationSeconds);

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        // 任务拒绝处理器:AbortPolicy 丢弃任务，抛运行时异常,CallerRunsPolicy 执行任务,DiscardPolicy 忽视，什么都不会发生,DiscardOldestPolicy 从队列中踢出最先进入队列（最后一个执行）的任务
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
