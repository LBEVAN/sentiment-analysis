package io.github.lbevan.rabbitmq.configuration;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;

public class AnalysisRequestMessageRecoverer extends RejectAndDontRequeueRecoverer {

    @Override
    public void recover(Message message, Throwable cause) {
        // delegate back to super to handle rabbitmq rejection processing
        super.recover(message, cause);
    }
}
