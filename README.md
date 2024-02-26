# Proyecto: Simulacion de Precios de Criptomonedas

## SpringBoot + Kafka + ElasticSearch + CloudKarafka
En este proyecto se prentende enfatizar el uso de la tecnologia Kafka, para la transmision y el consumo de datos.

Se creo un simulador de precios de criptomonedas, ya que se necesita un fuente de datos, para el envio de datos constante. No se opto, por consumir una API de plataformas, ya que esto limita el consumo de datos, a ciertas peticiones por mes y por minuto.

Con la ayuda de SpringBoot, con la notacion **@Scheduled** se genera una transmision de datos constante.
Con ayuda de Kafka, se trasmiten estos datos al servidor que esta alojado en **CloudKarafka**, para despues ser consumidos y guardados en **ElasticSearch**.


### Demo

Un vez con estos datos guardados en ElasticSearch, podemos generar una grafica en Kibana, para visualizar los precios de las criptomonedas.
![](https://github.com/IrvinACG/simuladorCriptos/blob/main/Dashboard-Crypto.png)
