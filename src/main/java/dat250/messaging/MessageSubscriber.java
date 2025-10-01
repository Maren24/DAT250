package dat250.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.stereotype.Component;

@Component
public class MessageSubscriber {
    private final static String QUEUE_NAME = "poll";
    private final static String EXCHANGE_NAME = "poll-exchange";
    private final ConnectionFactory factory;

    private final ObjectMapper mapper;

    public MessageSubscriber(ObjectMapper mapper) {
        factory = new ConnectionFactory();
        factory.setHost("localhost");

        this.mapper = mapper;
    }

    @PostConstruct
    public void startListening() {
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            // String queueName = QUEUE_NAME + "-" + poll_id;
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            // declare the same queue as the publisher
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            String bindingKey = "poll/#";
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, bindingKey);

            System.out.println(" [*] Waiting for messages.");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" +
                        delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
