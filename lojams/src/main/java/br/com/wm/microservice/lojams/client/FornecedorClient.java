package br.com.wm.microservice.lojams.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.wm.microservice.lojams.dto.InfoFornecedorDTO;
import br.com.wm.microservice.lojams.dto.InfoPedidoDTO;
import br.com.wm.microservice.lojams.dto.ItemDaCompraDTO;

@FeignClient("fornecedorms")
public interface FornecedorClient {

	@GetMapping("/info/{estado}")
	InfoFornecedorDTO getInfoPorEstado(@PathVariable String estado);

	@PostMapping("/pedido")
	InfoPedidoDTO realizaPedido(List<ItemDaCompraDTO> itens);
	
}
