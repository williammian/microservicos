package br.com.wm.microservice.lojams.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.wm.microservice.lojams.controller.dto.InfoFornecedorDTO;

@FeignClient("fornecedorms")
public interface FornecedorClient {

	@GetMapping("/info/{estado}")
	InfoFornecedorDTO getInfoPorEstado(@PathVariable String estado);
	
}
