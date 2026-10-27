package com.techrepair.notificaciones;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfig {

    // Dirección del broker Mosquitto.
    private static final String BROKER = "tcp://localhost:1883";

    // Tema al que nos suscribimos.
    private static final String TOPIC = "techrepair/reparaciones";

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter mqttInbound() {

        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        BROKER,
                        "techrepair-notificaciones",
                        TOPIC
                );

        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());

        return adapter;
    }

    @Bean
    @org.springframework.integration.annotation.ServiceActivator(
            inputChannel = "mqttInputChannel"
    )
    public MessageHandler recibirMensaje() {
        return mensaje -> {
            System.out.println("📩 Mensaje MQTT recibido:");
            System.out.println(mensaje.getPayload());
        };
    }
}
