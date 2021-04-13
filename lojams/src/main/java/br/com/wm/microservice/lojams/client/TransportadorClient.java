package br.com.wm.microservice.lojams.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.wm.microservice.lojams.dto.InfoEntregaDTO;
import br.com.wm.microservice.lojams.dto.VoucherDTO;

@FeignClient("transportadorms")
public interface TransportadorClient {
	
	@PostMapping("/entrega")
	public VoucherDTO reservaEntrega(@RequestBody InfoEntregaDTO pedidoDTO);

}
