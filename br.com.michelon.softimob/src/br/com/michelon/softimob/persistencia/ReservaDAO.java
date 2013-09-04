package br.com.michelon.softimob.persistencia;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.modelo.Reserva;

public interface ReservaDAO extends CrudRepository<Reserva, Long>{

	List<Reserva> findByImovel(Imovel imovel);

	List<Reserva> findByDataVencimentoBeforeAndResolvidoFalse(Date data);

	List<Reserva> findByDataVencimentoAfterAndImovelEquals(Date data, Imovel imovel);
	
}
