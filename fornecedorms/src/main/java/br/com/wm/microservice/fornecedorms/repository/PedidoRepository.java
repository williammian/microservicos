package br.com.wm.microservice.fornecedorms.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.wm.microservice.fornecedorms.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long>{

}
