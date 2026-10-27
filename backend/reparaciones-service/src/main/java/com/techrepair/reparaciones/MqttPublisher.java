
package com.techrepair.reparaciones;

import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;

@Configuration
public class MqttPublisher {

    // Dirección de nuestro broker Mosquitto
    private static final String BROKER = "tcp://localhost:1883";

    // Tema que escucha notificaciones-service
    private static final String TOPIC = "techrepair/reparaciones";

    @Bean
    public MqttPahoMessageHandler mqttOutbound() {

        MqttPahoMessageHandler handler =
                new MqttPahoMessageHandler(
                        BROKER,
                        "techrepair-reparaciones"
                );

        handler.setAsync(true);
        handler.setDefaultTopic(TOPIC);

        return handler;
    }


    public void publicar(String mensaje) {

        // Obtenemos el componente encargado de enviar mensajes.
        MqttPahoMessageHandler handler = mqttOutbound();

        // Construimos y enviamos el mensaje MQTT.
        handler.handleMessage(
                MessageBuilder.withPayload(mensaje).build()
        );

        System.out.println("📤 Mensaje MQTT enviado: " + mensaje);
    }

}
