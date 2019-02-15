package com.beta.sb.system;

import com.beta.sb.service.GitHubLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by yaoyt on 17/7/14.
 *
 * @author yaoyt
 */
@Component
public class CommandRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CommandRunner.class);

    private final GitHubLookupService gitHubLookupService;

    public CommandRunner(GitHubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }


    @Override
    public void run(String... strings) throws Exception {
        long start = System.currentTimeMillis();

        /*// Kick of multiple, asynchronous lookups
        Future<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
        Future<User> page2 = gitHubLookupService.findUser("CloudFoundry");
        Future<User> page3 = gitHubLookupService.findUser("Spring-Projects");

        // Wait until they are all done
        while (!(page1.isDone() && page2.isDone() && page3.isDone())) {
            Thread.sleep(10); //10-millisecond pause between each check
        }

        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info("--> " + page1.get());
        logger.info("--> " + page2.get());
        logger.info("--> " + page3.get());*/

    }
}