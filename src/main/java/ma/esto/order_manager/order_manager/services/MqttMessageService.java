package ma.esto.order_manager.order_manager.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.esto.order_manager.order_manager.Models.Order;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public class MqttMessageService {
    private final MessageChannel mqttOutboundChannel;
    private final ObjectMapper objectMapper;

    public MqttMessageService(MessageChannel mqttOutboundChannel) {
        this.mqttOutboundChannel = mqttOutboundChannel;
        this.objectMapper = new ObjectMapper();
    }

    public void sendMessage(Order order) {
        try {
            String orderToSend = objectMapper.writeValueAsString(order);
            mqttOutboundChannel.send(MessageBuilder.withPayload(orderToSend).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

