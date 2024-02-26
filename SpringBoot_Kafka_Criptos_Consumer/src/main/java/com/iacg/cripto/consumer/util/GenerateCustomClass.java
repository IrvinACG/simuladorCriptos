package com.iacg.cripto.consumer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Generar clases a partir de un String
 */
@Slf4j
@Component
public class GenerateCustomClass {
	
	/**
	 * Variable mapper
	 */
	@Autowired
	private ObjectMapper mapper;
	
	/**
	 * Metodo que genera una clase a partir de un String
	 * @param <T> tipo de objeto
	 * @param object objeto
	 * @param valueType tipo de clase
	 * @return <T> T tio de objeto
	 */
	public <T> T getClassFromString(String object, Class<T> valueType ) {
		T objectReturn = null;
		try {
			objectReturn = mapper.readValue(object, valueType);
		} catch (JsonProcessingException e) {
			log.error("Error al convertir(Mapear) String a Clase, error: {}", e.getMessage());
		}
		return objectReturn;
	}
}//Fin de clase