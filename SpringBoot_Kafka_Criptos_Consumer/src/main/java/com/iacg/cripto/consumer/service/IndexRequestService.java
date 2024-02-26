package com.iacg.cripto.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import lombok.extern.slf4j.Slf4j;

/**
 * Servicio que contiene los metodos para guardar datos en ElasticSearch
 */
@Slf4j
@Service
public class IndexRequestService implements IIndexRequestService{
	
	/**
	 * Variable asyncClient
	 */
	@Autowired
	private ElasticsearchAsyncClient asyncClient;
	
	/**
	 * Metodo que envia de forma asincrona a ElasticSearch y los guarda en el indice
	 * @param index indice
	 * @param id identificador 
	 * @param document documento
	 */
	@Override
	public void sendRequest(String index, String id, Object document) {
		asyncClient.index(i -> i
				.index(index)
				.id(id)
				.document(document)
			).whenComplete((response, exception) -> {
			    if (exception != null) {
			        log.error("Fallo al insertar index, error: {}, objeto {}",exception.getMessage() ,document.toString());
			    } else {
			        log.debug("Indexado con id: {}", response.id());
			    }
			});
	}

}//Fin de clase
