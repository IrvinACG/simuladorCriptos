package com.iacg.cripto.producer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase que crea un String a partir de una clase
 */
@Slf4j
@Component
public class GenerateString {

	/**
	 * Variable mapper
	 */
	@Autowired
	private ObjectMapper mapper;
	
	/**
	 * Metodo que crea un String a partir de un Object
	 * @param object
	 * @return
	 */
	public String stringFromClass(Object object) {
		String customString = null;
		
		try {
			customString = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("Error al convtir Clase a String, error: {}",e.getMessage());
		}
		
		return customString;
	}
	
}//Fin de clase
