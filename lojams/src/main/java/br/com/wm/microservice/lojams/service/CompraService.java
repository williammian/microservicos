package br.com.wm.microservice.lojams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.wm.microservice.lojams.controller.dto.CompraDTO;
import br.com.wm.microservice.lojams.controller.dto.InfoFornecedorDTO;

@Service
public class CompraService {
	
	@Autowired
	private RestTemplate client;
	
	@Autowired
	private DiscoveryClient eurekaClient;

	public void realizaCompra(CompraDTO compra) {
		
		ResponseEntity<InfoFornecedorDTO> exchange = 
				client.exchange("http://fornecedorms/info/" + compra.getEndereco().getEstado(),
				HttpMethod.GET, null, InfoFornecedorDTO.class);
		
		eurekaClient.getInstances("fornecedorms").stream()
		.forEach(fornecedorms -> {
			System.out.println(fornecedorms.getHost() + ":" + fornecedorms.getPort());
		});
		
		System.out.println(exchange.getBody().getEndereco());
		
	}

}
