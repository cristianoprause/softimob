package br.com.michelon.softimob.persistencia;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.modelo.ChamadoReforma;

public interface ChamadoReformaDAO extends CrudRepository<ChamadoReforma, Long>{

	List<ChamadoReforma> findByAluguel(Aluguel aluguel);

	Collection<ChamadoReforma> findByDataBeforeAndFinalizacaoIsNull(Date dataVencimento);

}
