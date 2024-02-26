package com.iacg.cripto.producer.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase que representa modelo de informacion de Criptomoneda
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cripto {

	/**
	 * Variable high: El precio mayor de criptomoneda
	 */
	private Double high;
	
	/**
	 * Variable last: ultimo precio de venta (trade)
	 */
	private Double last;
	
	/**
	 * Variable created_at: Fecha de cuando de obtuvo el precio
	 */
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date created_at;
	
	/**
	 * Variable book: tipo de conversion de criptomoneda
	 */
	private String book;
	
	/**
	 * Variable volume: cantidad de unidades de una criptomoneda que se negocian
	 */
	private Double volume;
	
	/**
	 * Variable vwap: El precio promedio
	 */
	private Double vwap;
	
	/**
	 * Variable low: El precio más bajo
	 */
	private Double low;
	
	/**
	 * Variable ask: La orden de venta más baja
	 */
	private Double ask;
	
	/**
	 * Variable bid: La orden de compra más alta
	 */
	private Double bid;

}//Fin de clase
