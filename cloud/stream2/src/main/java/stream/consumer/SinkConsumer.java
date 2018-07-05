package stream.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class SinkConsumer {
    private static Logger logger = LoggerFactory.getLogger(SinkConsumer.class);

    @StreamListener(Sink.INPUT) //监听接收消息
    public void receive(Object payload) {
        logger.info("Received: " + payload);
    }
}
