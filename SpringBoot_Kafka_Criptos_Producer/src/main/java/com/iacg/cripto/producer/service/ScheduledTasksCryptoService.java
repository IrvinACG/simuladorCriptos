package com.iacg.cripto.producer.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.iacg.cripto.producer.model.Cripto;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio que cotiene los metodos para iniciar la simulacion
 */
@Slf4j
@Service
public class ScheduledTasksCryptoService implements IScheduledTasksCryptoService{

	/**
	 * Variable simulatorCrypto
	 */
	@Autowired
	public ISimulatorPricesCryptoService simulatorCrypto;
	
	/**
	 * Variable producerKafka
	 */
	@Autowired
	public IProducerKafkaService producerKafka;
	
	/**
	 * Variable topicCrypto
	 */
	@Value("${cloud-karafka.topic-crypto}")
	public String topicCrypto;
	
	/**
	 * Variable xrp
	 */
	public Cripto xrp = null;
	
	/**
	 * Variable bitcoin
	 */
	public Cripto bitcoin = null;
	
	/*
	 * Metodo que genera los datos de la cripto XRP
	 */
	@Scheduled(fixedDelay = 5000)
	@Override
	public void dataXrp() {
		if(Objects.isNull(xrp)) {
			xrp = simulatorCrypto.simulatePrice("xrp_mxn", 15.0);
		}
		log.info("CRIPTO XRP: {}",xrp.toString());
		xrp = simulatorCrypto.updatePrice(xrp, 1);
		//Enviar datos a kafka
		this.producerKafka.sendMessage(this.topicCrypto, xrp);
	}

	/*
	 * Metodo que genera los datos de la cripto Bitcoin
	 */
	@Scheduled(fixedDelay = 5000)
	@Override
	public void dataBitcoin() {
		if(Objects.isNull(bitcoin)) {
			bitcoin = simulatorCrypto.simulatePrice("btc_mxn", 950000.0);
		}
		log.info("CRIPTO BTC: {}",bitcoin.toString());
		bitcoin = simulatorCrypto.updatePrice(bitcoin, 10000);
		//Enviar datos a kafka
		this.producerKafka.sendMessage(this.topicCrypto, bitcoin);
	}

}//Fin de clase
