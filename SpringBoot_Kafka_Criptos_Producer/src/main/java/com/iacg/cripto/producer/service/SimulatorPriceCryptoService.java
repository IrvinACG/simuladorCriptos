package com.iacg.cripto.producer.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.iacg.cripto.producer.model.Cripto;

@Service
public class SimulatorPriceCryptoService implements ISimulatorPricesCryptoService{

	@Override
	public Cripto simulatePrice(String book, double maxPrice) {
		
		Cripto c = new Cripto();
		
		double high = (Math.random() * maxPrice) + 1;
		c.setHigh(high);
		double low = high - ((Math.random() * 0.9) + 0.001);
		c.setLow(low);
		c.setLast(low);
		double bid = low - ((Math.random() * low) + 0.001);
		c.setBid(bid);
		double ask = bid - ((Math.random() * bid) + 0.001);
		c.setAsk(ask);
		double volume = (Math.random() * 100000);
		c.setVolume(volume);
		double vwap = (high + low) / 2;
		c.setVwap(vwap);
		c.setBook(book);
		c.setCreated_at(new Date());
		
		return c;
	}

	@Override
	public Cripto updatePrice(Cripto crypto, double difPrice) {
		Cripto c = new Cripto();
		double high = 0.0;
		if(isPar()) { //Suma precio
			high = crypto.getHigh() + ((Math.random() * difPrice) + 0.001);
		}else { //Resta precio
			high = crypto.getHigh() - ((Math.random() * difPrice) + 0.001);
		}
		c.setHigh(high);
		double low = high - ((Math.random() * 0.9) + 0.001);
		c.setLow(low);
		c.setLast(low);
		double bid = low - ((Math.random() * low) + 0.001);
		c.setBid(bid);
		double ask = bid - ((Math.random() * bid) + 0.001);
		c.setAsk(ask);
		double volume = (Math.random() * 100000);
		c.setVolume(volume);
		double vwap = (high + low) / 2;
		c.setVwap(vwap);
		c.setBook(crypto.getBook());
		c.setCreated_at(new Date());
		return c;
	}

	public boolean isPar() {
		int num = (int) (Math.random() * 10) + 1;
		return num % 2 == 0;
	}
	
}
