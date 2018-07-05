package com.stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@EnableBinding(value = {ApplicationTest.SinkSend.class})
public class ApplicationTest {
    @Autowired
    private SinkSend sinkSend;

    @Test
    public void sinkSenderTester() {
        sinkSend.output().send(MessageBuilder.withPayload("消息测试").build());
    }

    public interface SinkSend {
        String TYPE = "input";  //自定义通道名

        @Output(SinkSend.TYPE)  //发送消息
        MessageChannel output();
    }

}