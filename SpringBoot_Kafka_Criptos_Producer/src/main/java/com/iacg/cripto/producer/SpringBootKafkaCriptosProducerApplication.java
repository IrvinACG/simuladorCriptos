package com.iacg.cripto.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Clase principal del proyecto
 */
@EnableScheduling
@SpringBootApplication
public class SpringBootKafkaCriptosProducerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaCriptosProducerApplication.class, args);
	}

}//Fin de clase
