package com.iacg.cripto.consumer.service;

/**
 * Interfaz que contiene los metodos para guardar datos en ElasticSearch
 */
public interface IIndexRequestService {

	/**
	 * Metodo que envia de forma asincrona a ElasticSearch y los guarda en el indice
	 * @param index indice
	 * @param id identificador 
	 * @param document documento
	 */
	void sendRequest(String index, String id, Object document);
	
}//Fin de clase
