package com.beta.sb.thread;

import com.beta.sb.async.AsyncTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by yaoyt on 17/5/23.
 *
 * @author yaoyt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= MockServletContext.class)
@WebAppConfiguration
public class TaskTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void AsyncTaskTest() throws InterruptedException, ExecutionException {
        Future<String> task1 = asyncTask.doTask1();
        Future<String> task2 = asyncTask.doTask2();

        while(true) {
            if(task1.isDone() && task2.isDone()) {
                logger.info("Task1 result: {}", task1.get());
                logger.info("Task2 result: {}", task2.get());
                break;
            }
            Thread.sleep(1000);
        }
        logger.info("All tasks finished.");
    }

}
