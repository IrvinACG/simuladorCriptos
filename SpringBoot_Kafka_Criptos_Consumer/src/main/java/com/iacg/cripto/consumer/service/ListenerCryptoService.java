package com.iacg.cripto.consumer.service;

import java.util.Objects;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.iacg.cripto.consumer.model.Cripto;
import com.iacg.cripto.consumer.util.GenerateCustomClass;
import com.iacg.cripto.consumer.util.IndexRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio que cotiene los metodos para consumir los datos Kafka
 */
@Slf4j
@Service
public class ListenerCryptoService extends IndexRequest implements IListenerCryptoService{

	/**
	 * Variable customClass
	 */
	@Autowired
	private GenerateCustomClass customClass;
	
	/**
	 * Variable indexRequestService
	 */
	@Autowired
	private IndexRequestService indexRequestService;
	
	/**
	 * Metodo que procesa el mensaje
	 * @param record ConsumerRecord
	 */
	@KafkaListener(topics = "${cloud-karafka.topic-crypto}")
	@Override
	public void proccessMessage(ConsumerRecord<String, String> record) {
		// Convertir String a Clase
		Cripto cripto = this.customClass.getClassFromString(record.value(), Cripto.class);
		
		if(!Objects.isNull(cripto)) { //Enviar datos a ElasticSearch asincrono
			String id = String.format("%s-%s-%s", record.partition(), record.offset(), record.key());
			this.indexRequestService.sendRequest(this.indexCrypto, id, cripto);
		}
		
		log.info("Fecha: {}",cripto.getCreated_at().toString());
		
		//log.info("Consumer, Cripto: {}",cripto.toString());
	}

}//Fin de clase
