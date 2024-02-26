package com.iacg.cripto.producer.service;

import com.iacg.cripto.producer.model.Cripto;

public interface ISimulatorPricesCryptoService {
	
	Cripto updatePrice(Cripto crypto, double difPrice);
	
	Cripto simulatePrice(String book, double maxPrice);
	
}
