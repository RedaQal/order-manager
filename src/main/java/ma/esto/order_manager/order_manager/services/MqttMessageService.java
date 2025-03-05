package ma.esto.order_manager.order_manager.services;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public class MqttMessageService {
    private final MessageChannel mqttOutboundChannel;
    public MqttMessageService(MessageChannel mqttOutboundChannel) {
        this.mqttOutboundChannel = mqttOutboundChannel;
    }
    public void sendMessage(String message) {
        try{
            mqttOutboundChannel.send(MessageBuilder.withPayload(message).build());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
