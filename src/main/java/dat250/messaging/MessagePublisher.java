package dat250.messaging;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import dat250.models.Vote;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {
    private final static String QUEUE_NAME = "poll";
    private final static String EXCHANGE_NAME = "poll-exchange";
    private final ConnectionFactory factory;

    private final ObjectMapper mapper;

    public MessagePublisher(ObjectMapper mapper) {
        factory = new ConnectionFactory();
        factory.setHost("localhost");

        this.mapper = mapper;
    }

    public void createChannel(String poll_id) throws Exception {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            // String QueueName = QUEUE_NAME + "-" + poll_id;
            String QueueName = QUEUE_NAME;
            String ExchangeName = EXCHANGE_NAME;

            // Specifiy exchange on using topics
            channel.exchangeDeclare(ExchangeName, "topic");

            // Create a new queue
            channel.queueDeclare(QueueName, false, false, false, null);

            // Bind queue and exchange
            channel.queueBind(QueueName, EXCHANGE_NAME, "poll/" + poll_id);

            // channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            // System.out.println(" [x] Sent '" + message + "'");
        }
    }

    public void sendMessage(Vote vote, String poll_id) throws Exception {
        String ExchangeName = EXCHANGE_NAME;
        String QueueName = QUEUE_NAME;
        String RoutingKey = "poll/" + poll_id;

        System.out.println("Sending Message: " + vote);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Specifiy exchange on using topics
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            // Create a new queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // Bind queue and exchange
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "poll/" + poll_id);

            String message = mapper.writeValueAsString(vote);
            channel.basicPublish(EXCHANGE_NAME, RoutingKey, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "' on queue: " + QueueName);
        }
    }
}
