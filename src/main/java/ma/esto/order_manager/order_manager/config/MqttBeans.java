package ma.esto.order_manager.order_manager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.esto.order_manager.order_manager.Models.Order;
import ma.esto.order_manager.order_manager.repositories.OrderRepository;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttBeans {
    @Value("${mqtt.broker.url}")
    private String BROKER_URL;
    @Value("${mqtt.client.id}")
    private String CLIENT_ID;
    @Value("${mqtt.topic}")
    private String TOPIC;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OrderRepository orderRepository;

    public MqttBeans(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setServerURIs(new String[]{BROKER_URL});
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(CLIENT_ID, mqttClientFactory(), TOPIC);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return ((message) -> {
            try {
                String payload = message.getPayload().toString();
                Order order = objectMapper.readValue(payload, Order.class);
                orderRepository.save(order);
                System.out.println("Received message: " + message.getPayload());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler(CLIENT_ID + "_out", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(TOPIC);
        return messageHandler;
    }
}