package com.iacg.cripto.producer.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.iacg.cripto.producer.util.GenerateString;

/**
 * Servicio que contiene los metodos para el envio de mensajes Kafka
 */
@Service
public class ProducerKafkaService implements IProducerKafkaService{

	/**
	 * Variable kafkaTemplate
	 */
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	/**
	 * Variable generateString
	 */
	@Autowired
	private GenerateString generateString;
	
	/**
	 * Metodo que envia el mensaje
	 * @param topic topic
	 * @param message mensaje
	 */
	@Override
	public void sendMessage(String topic, Object object) {
		//Generar llave aleatoria
		String key = UUID.randomUUID().toString();
		String message = generateString.stringFromClass(object);
		//Enviar mensaje a Kafka
		this.kafkaTemplate.send(topic, key, message);
		
	}

}
