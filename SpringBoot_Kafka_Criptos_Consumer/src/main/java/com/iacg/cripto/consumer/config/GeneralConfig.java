package com.iacg.cripto.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase de configuracion general
 */
@Configuration
public class GeneralConfig {

	/**
	 * Metodo que crea mapper
	 * @return ObjectMapper
	 */
	@Bean
	ObjectMapper createMapper() {
		return new ObjectMapper();
	}
	
}//Fin de clase
