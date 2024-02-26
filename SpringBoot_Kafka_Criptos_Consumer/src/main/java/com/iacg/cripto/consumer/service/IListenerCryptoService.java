package com.iacg.cripto.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * Interfaz que cotiene los metodos para consumir los datos Kafka
 */
public interface IListenerCryptoService {

	/**
	 * Metodo que procesa el mensaje
	 * @param record ConsumerRecord
	 */
	void proccessMessage(ConsumerRecord<String, String> record);
	
}//Fin de clase
