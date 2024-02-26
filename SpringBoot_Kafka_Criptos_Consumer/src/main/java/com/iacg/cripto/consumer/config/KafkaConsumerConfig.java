package com.iacg.cripto.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

/**
 * Clase de configuracion de Consumer Kafka
 */
@Configuration
public class KafkaConsumerConfig {
	
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
	 * Variable groupId
	 */
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;
	
	/**
	 * Metodo donde se asignan las configuraciones generales
	 * del Consumer
	 * @return Map<String, Object>
	 */
	Map<String, Object> consumerConfig(){
		Map<String, Object> prop = new HashMap<>();
		prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
		prop.put(ConsumerConfig.GROUP_ID_CONFIG, this.groupId);
		prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		prop.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"" + this.user + "\" password=\"" + this.pass + "\";"); 
		prop.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, this.securityProtocol);
		prop.put(SaslConfigs.SASL_MECHANISM, this.saslMechanism);
		

		return prop;
	}
	
	/**
	 * Metodo que crea ConsumerFactory y carga las configuraciones
	 * generales
	 * @return ConsumerFactory
	 */
	@Bean
	ConsumerFactory<String, String> consumerFactory(){
		return new DefaultKafkaConsumerFactory<>(consumerConfig());
	}
	
	/**
	 * Metodo que crea ConcurrentKafkaListenerContainerFactory y carga el 
	 * consumerFactory
	 * @return ConcurrentKafkaListenerContainerFactory
	 */
	@Bean
	ConcurrentKafkaListenerContainerFactory<String, String> listenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, String> listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		listenerContainerFactory.setConsumerFactory(consumerFactory());
		listenerContainerFactory.setConcurrency(10); //Hilos
		return listenerContainerFactory;
	}
	
}//Fin de clase
