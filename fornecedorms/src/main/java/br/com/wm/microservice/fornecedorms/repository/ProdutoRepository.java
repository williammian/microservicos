package br.com.wm.microservice.fornecedorms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.wm.microservice.fornecedorms.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long>{

	List<Produto> findByEstado(String estado);
	
	List<Produto> findByIdIn(List<Long> ids);
}
