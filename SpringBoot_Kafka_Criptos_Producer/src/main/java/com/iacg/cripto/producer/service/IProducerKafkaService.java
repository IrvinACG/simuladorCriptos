package com.iacg.cripto.producer.service;

/**
 * Interfaz que contiene los metodos para el envio de mensajes Kafka
 */
public interface IProducerKafkaService {

	/**
	 * Metodo que envia el mensaje
	 * @param topic topic
	 * @param message mensaje
	 */
	void sendMessage(String topic, Object object);
	
}//Fin de clase
