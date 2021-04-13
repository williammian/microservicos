package br.com.wm.microservice.transportadorms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wm.microservice.transportadorms.dto.EntregaDTO;
import br.com.wm.microservice.transportadorms.dto.VoucherDTO;
import br.com.wm.microservice.transportadorms.service.EntregaService;

@RestController
@RequestMapping("/entrega")
public class EntregaController {
	
	@Autowired
	private EntregaService entregaService;

	@PostMapping
	public VoucherDTO reservaEntrega(@RequestBody EntregaDTO pedidoDTO) {
		return entregaService.reservaEntrega(pedidoDTO);
	}
}
