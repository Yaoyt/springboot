package com.beta.sb.config;

import com.beta.sb.Application;
import com.beta.sb.domain.second.MessageRepository;
import com.beta.sb.domain.primary.User;
import com.beta.sb.domain.primary.UserRepository;
import com.beta.sb.domain.second.Message;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yaoyt on 17/5/5.
 *
 * @author yaoyt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RepositoryConfigTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;


    @Test
    public void test() throws Exception {
        userRepository.save(new User("aaa", 10));
        userRepository.save(new User("bbb", 20));
        userRepository.save(new User("ccc", 30));
        userRepository.save(new User("ddd", 40));
        userRepository.save(new User("eee", 50));
        Assert.assertEquals(5, userRepository.findAll().size());
        messageRepository.save(new Message("o1", "aaaaaaaaaa"));
        messageRepository.save(new Message("o2", "bbbbbbbbbb"));
        messageRepository.save(new Message("o3", "cccccccccc"));
        Assert.assertEquals(3, messageRepository.findAll().size());
    }
}
