# Messaging with RabbitMQ

*Student*: Maren Franke

*Code*: 


## Using Rabbit MQ

Start in Docker:
```bash
docker run -d --hostname my-rabbit --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management
```

Webpage reachable at: http://localhost:15672
- Default Password and Username: `guest`

## Idea

### MessagePublisher

1. Register new Topics to the Exchange `poll-exchange` and the connected Channel `poll` with the topic `poll/<poll_id>`
2. Send Messages on a new `vote` with the payload of the vote and to the topic `poll/<poll_id>`

### MessageSubscriber

Initially executed and invoked into the PollManager.
1. Registers to the path `poll/#` and receives all messages send there
2. "Stores" data into the database (only theoretically)

## Challenges

- One challenge was to send JSON payload to the stream, because a few dependencies were missing - but it was solved
- Another challenge was the Port-Conflict I faced inbetween and a conflict in the Port-Mapping from WSL to Windows - but it was also solved


