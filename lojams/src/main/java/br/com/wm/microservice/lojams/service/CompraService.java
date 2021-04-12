package br.com.wm.microservice.lojams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wm.microservice.lojams.client.FornecedorClient;
import br.com.wm.microservice.lojams.controller.dto.CompraDTO;
import br.com.wm.microservice.lojams.controller.dto.InfoFornecedorDTO;

@Service
public class CompraService {
	
	@Autowired
	private FornecedorClient fornecedorClient;

	public void realizaCompra(CompraDTO compra) {
		
		InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		
		System.out.println(info.getEndereco());
		
	}

}
