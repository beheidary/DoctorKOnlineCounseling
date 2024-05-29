package com.doctork.doctorkonlinecounseling.Infrastructure.Observer;
import com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine.ElasticRepository;
import com.doctork.doctorkonlinecounseling.configuration.RabbitMQConfig.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import com.doctork.doctorkonlinecounseling.domain.RabbitMqMessages.CostumeMessage;
@Service
public class PhysicianListener {

    private final ElasticRepository elasticRepository;
    private final RabbitTemplate rabbitTemplate;

    public PhysicianListener(RabbitTemplate rabbitTemplate, ElasticRepository elasticRepository) {
        this.elasticRepository = elasticRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.MAIN_QUEUE)
    public void physicianHandler(CostumeMessage message, Message amqpMessage) {
        try {
            switch (message.getMessageType()){
                case changeState -> elasticRepository.changeState(message.getNationalCode(), message.getState());
                case changeStatus -> elasticRepository.changeStatus(message.getNationalCode(), message.getStatus());
                case addDocs -> elasticRepository.addPhysician(message);
            }

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
