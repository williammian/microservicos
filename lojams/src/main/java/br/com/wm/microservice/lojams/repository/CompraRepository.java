package br.com.wm.microservice.lojams.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.wm.microservice.lojams.model.Compra;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Long>{

}
