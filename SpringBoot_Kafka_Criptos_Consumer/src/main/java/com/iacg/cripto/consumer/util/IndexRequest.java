package com.iacg.cripto.consumer.util;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;

/**
 * Clase que contine los indices de ElasticSearch
 */
@Getter
public class IndexRequest {

	/**
	 * Variable INDEX_CRYPTO: cryto-index
	 */
	@Value("${elastic-search.index-crypto}")
	public String indexCrypto;
	
}//Fin de clase
