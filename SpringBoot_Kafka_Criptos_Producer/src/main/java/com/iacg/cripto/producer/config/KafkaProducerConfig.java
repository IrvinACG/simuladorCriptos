package com.iacg.cripto.producer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducerConfig {
	
	/**
	 * Variable user
	 */
	@Value("${cloud-karafka.user}")
	private String user;
	
	/**
	 * Variable password
	 */
	@Value("${cloud-karafka.pass}")
	private String pass;
	
	/**
	 * Variable saslMechanism;
	 */
	@Value("${spring.kafka.properties.sasl.mechanism}")
	private String saslMechanism;
	
	/**
	 * Variable securityProtocol
	 */
	@Value("${spring.kafka.properties.security.protocol}")
	private String securityProtocol;
	
	/**
	 * Variable bootstrapServers
	 */
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	
	/**
	 * Metodo donde se asignan las configuraciones generales
	 * del Producer
	 * @return Map<String, Object>
	 */
	Map<String, Object> producerConfig(){
		Map<String, Object> prop = new HashMap<>();
		prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
		prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		prop.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, false);	
		prop.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, this.securityProtocol);
		prop.put(SaslConfigs.SASL_MECHANISM, this.saslMechanism);
		prop.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"" + this.user + "\" password=\"" + this.pass + "\";"); 
		
		return prop;
	}

	/**
	 * Metodo que crea un KafkaTemplate y carga las configuraciones
	 * generales
	 * @return KafkaTemplate
	 */
	@Bean
	KafkaTemplate<String, String> kafkaTemplate(){
		DefaultKafkaProducerFactory<String, String> defaultKafkaProducerFactory = new DefaultKafkaProducerFactory<>(producerConfig());
		KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(defaultKafkaProducerFactory);
		return kafkaTemplate;
	}
	
}//Fin de clase
