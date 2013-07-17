package br.com.michelon.softimob.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.michelon.softimob.modelo.ModeloContrato;

public interface ModeloContratoDAO extends CrudRepository<ModeloContrato, Long>{

	@Query("SELECT r FROM ModeloContrato r WHERE r.ativo = :ativo")
	List<ModeloContrato> findAtivo(@Param(value= "ativo") Boolean ativo);

}
