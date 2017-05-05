package com.beta.sb.Repository;

import com.beta.sb.domain.second.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yaoyt on 17/5/5.
 *
 * @author yaoyt
 */
public interface MessageRepository extends JpaRepository<Message,Long> {


}
