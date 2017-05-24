package com.beta.sb.web.rest;

import com.beta.sb.async.AsyncTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * Created by yaoyt on 17/5/23.
 *
 * @author yaoyt
 */
@RestController
public class TaskController {

    @Autowired
    private AsyncTask asyncTask;

    private Logger logger = LoggerFactory.getLogger(AsyncTask.class);


    @RequestMapping("/task")
    public String index() throws  Exception{

        Future<String> task1 = asyncTask.doTask1();
        Future<String> task2 = asyncTask.doTask2();
        //Future<String> task3 = asyncTask.doTask3("33333333333333");
        //Future<String> task4 = asyncTask.doTask3("44444444444444");



        while(true) {
            if(task1.isDone() && task2.isDone() ) {
                logger.info("Task1 result: {}", task1.get());
                logger.info("Task2 result: {}", task2.get());
                break;
            }
            Thread.sleep(1000);
        }

        logger.info("All tasks finished.");
        return "";
    }

    @RequestMapping("/task2")
    public String index2() throws  Exception{
        final CountDownLatch latch = new CountDownLatch(2);

        Future<String> task3 = asyncTask.doTask3(latch,"33333333333333");
        Future<String> task4 = asyncTask.doTask3(latch,"44444444444444");

        //latch.await();
        logger.info("Task3 result: {}", task3.get());
        logger.info("Task4 result: {}", task4.get());

        logger.info("All tasks finished.");
        return "";
    }
}
