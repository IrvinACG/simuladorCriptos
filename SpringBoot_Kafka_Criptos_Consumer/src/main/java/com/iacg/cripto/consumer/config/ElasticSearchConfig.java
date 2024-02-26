package com.iacg.cripto.consumer.config;

import java.util.List;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.util.ContentType;

/**
 * Clase de configuracion de ElasticSearch
 */
@Configuration
public class ElasticSearchConfig {
	
	/**
	 * Variable user
	 */
	@Value("${elastic-search.user}")
	private String user;
	
	/**
	 * Variable pass
	 */
	@Value("${elastic-search.pass}")
	private String pass;
	
	/**
	 * Variable host
	 */
	@Value("${elastic-search.host}")
	private String host;
	
	/**
	 * Variable port
	 */
	@Value("${elastic-search.port}")
	private String port;
	
	/**
	 * Metodo que crea un cliente de ElasticSearch
	 * @return RestClient
	 */
    @Bean
    RestClient getRestClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(this.user, this.pass));
        return RestClient.builder(new HttpHost(this.host, Integer.parseInt(this.port)))
                .setHttpClientConfigCallback(httpClientBuilder -> {
                    httpClientBuilder.disableAuthCaching();
                    httpClientBuilder.setDefaultHeaders(List.of(
                            new BasicHeader(
                                    HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON)));
                    httpClientBuilder.addInterceptorLast((HttpResponseInterceptor)
                            (response, context) ->
                                    response.addHeader("X-Elastic-Product", "Elasticsearch"));
                    return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                }).build();
    }

    /**
     * Metodo que crea un ElasticsearchTransport
     * @return ElasticsearchTransport
     */
    @Bean
    ElasticsearchTransport getElasticsearchTransport() {
        return new RestClientTransport(getRestClient(), new JacksonJsonpMapper());
    }
    
    /**
     * Metodo que crea ElasticsearchClient sincrono
     * @return ElasticsearchClient
     */
    @Bean
    ElasticsearchClient getElasticsearchClient() {
        return new ElasticsearchClient(getElasticsearchTransport());
    }
    
    /**
     * Meotodo que crea ElasticsearchAsyncClient asincrono
     * @return ElasticsearchAsyncClient
     */
    @Bean
    ElasticsearchAsyncClient getElasticsearchAsyncClient() {
    	return new ElasticsearchAsyncClient(getElasticsearchTransport());
    }
    
}//Fin de clase
