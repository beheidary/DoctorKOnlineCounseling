package com.doctork.doctorkonlinecounseling.Infrastructure;
import com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine.ElasticRepository;
import com.doctork.doctorkonlinecounseling.configuration.RabbitMQConfig.RabbitMQConfig;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import com.doctork.doctorkonlinecounseling.domain.RabbitMqMessages.PhysicianStatusChangeMessage;
@Service
public class PhysicianStatusChangeListener {

    private final ElasticRepository elasticRepository;
    private final RabbitTemplate rabbitTemplate;

    public PhysicianStatusChangeListener(RabbitTemplate rabbitTemplate, ElasticRepository elasticRepository) {
        this.elasticRepository = elasticRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.MAIN_QUEUE)
    public void handlePhysicianStatusChange(PhysicianStatusChangeMessage message, Message amqpMessage) {
        try {
            elasticRepository.changeStatus(message.getNationalCode(), message.getStatus());
        } catch (Exception e) {
            Integer retryCount = (Integer) amqpMessage.getMessageProperties().getHeaders().get("x-retry-count");
            if (retryCount == null) {
                retryCount = 0;
            }

            if (retryCount >= 1) {
                rabbitTemplate.convertAndSend(RabbitMQConfig.DEAD_LETTER_EXCHANGE, RabbitMQConfig.DEAD_LETTER_QUEUE, message);
            } else {
                retryCount++;
                MessageProperties properties = amqpMessage.getMessageProperties();
                properties.setHeader("x-retry-count", retryCount);
                Integer finalRetryCount = retryCount;
                rabbitTemplate.convertAndSend(RabbitMQConfig.RETRY_EXCHANGE, RabbitMQConfig.RETRY_QUEUE, message, msg -> {
                    msg.getMessageProperties().setHeader("x-retry-count", finalRetryCount);
                    return msg;
                });
            }
        }
    }
}
