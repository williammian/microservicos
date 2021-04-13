package br.com.wm.microservice.lojams.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.wm.microservice.lojams.client.FornecedorClient;
import br.com.wm.microservice.lojams.client.TransportadorClient;
import br.com.wm.microservice.lojams.dto.CompraDTO;
import br.com.wm.microservice.lojams.dto.InfoEntregaDTO;
import br.com.wm.microservice.lojams.dto.InfoFornecedorDTO;
import br.com.wm.microservice.lojams.dto.InfoPedidoDTO;
import br.com.wm.microservice.lojams.dto.VoucherDTO;
import br.com.wm.microservice.lojams.model.Compra;
import br.com.wm.microservice.lojams.repository.CompraRepository;

@Service
public class CompraService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);
	
	@Autowired
	private FornecedorClient fornecedorClient;
	
	@Autowired
	private TransportadorClient transportadorClient;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@HystrixCommand(threadPoolKey = "getByIdThreadPool")
	public Compra getById(Long id) {
		return compraRepository.findById(id).orElse(new Compra());
	}

	@HystrixCommand(fallbackMethod = "realizaCompraFallBack",
			threadPoolKey = "realizaCompraThreadPool")
	public Compra realizaCompra(CompraDTO compra) {
		
		final String estado = compra.getEndereco().getEstado();
		
		LOG.info("Buscando informações do fornecedor de {}", estado);
		InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(estado);
		
		LOG.info("Realizando um pedido");
		InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());
		
		LOG.info("Realizando um voucher de entrega");
		InfoEntregaDTO entregaDTO = new InfoEntregaDTO();
		entregaDTO.setPedidoId(pedido.getId());
		entregaDTO.setDataParaEntrega(LocalDate.now().plusDays(pedido.getTempoDePreparo()));
		entregaDTO.setEnderecoOrigem(info.getEndereco());
		entregaDTO.setEnderecoDestino(compra.getEndereco().toString());
		VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDTO);
		
		LOG.info("Salvando compra");
		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(pedido.getId());
		compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		compraSalva.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
		compraSalva.setVoucher(voucher.getNumero());
		compraRepository.save(compraSalva);
		
//		try { //usado para testar o circuit breaker
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		LOG.info("Terminou compra");
		
		return compraSalva;		
	}
	
	public Compra realizaCompraFallBack(CompraDTO compra) {
		Compra compraFallback = new Compra();
		compraFallback.setEnderecoDestino(compra.getEndereco().toString());
		return compraFallback;
	}

}
