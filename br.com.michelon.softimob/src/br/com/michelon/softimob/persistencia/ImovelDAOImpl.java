package br.com.michelon.softimob.persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.michelon.softimob.modelo.Comodo;
import br.com.michelon.softimob.modelo.ContratoPrestacaoServico.TipoContrato;
import br.com.michelon.softimob.modelo.Imovel;
import br.com.michelon.softimob.tela.view.BuscaAvancadaImovelView.ModeloBusca;

@Repository
public class ImovelDAOImpl {

	@PersistenceContext
	private EntityManager em;

	public List<Imovel> buscaAvancada(ModeloBusca m){
		Date dataHoje = new Date();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT distinct i FROM Imovel i ");
		sb.append("LEFT JOIN ContratoPrestacaoServico c on c.imovel = i ");
		sb.append("LEFT JOIN Reserva r on r.imovel = i ");
		
		List<Comodo> comodos = m.getComodos();
		for(int p = 0 ; p < comodos.size(); p++)
			sb.append(String.format("JOIN Comodo c%s on c%s.imovel = i ", p, p));

		sb.append("WHERE ");
		
		if(m.getCodigo() != null)
			sb.append("AND (i.id = :codigo) ");
		if(m.getMetroMin() != null)
			sb.append("AND :metroMin <= i.metragem ");
		if(m.getMetroMax() != null)
			sb.append("AND :metroMax >= i.metragem ");
		if(m.getValMin() != null)
			sb.append("AND :valMin <= c.valor ");
		if(m.getValMax() != null)
			sb.append("AND :valMax >= c.valor ");
		if(m.getAngariador() != null)
			sb.append("AND :angariador = i.angariador ");
		if(m.getProprietario() != null)
			sb.append("AND i.proprietario = :proprietario ");
		if(m.getTipoImovel() != null)
			sb.append("AND i.tipo = :tipoImovel ");
		if(m.getBairro() != null)
			sb.append("AND i.endereco.rua.bairro = :bairro ");
		if(m.getCidade() != null)
			sb.append("AND i.endereco.rua.bairro.cidade = :cidade ");
		if(m.getObservacoes() != null)
			sb.append("AND i.observacoes like CONCAT('%', :observacoes, '%') ");
		if(!(m.isReservado() && m.isNaoReservado())){
			sb.append("AND (((:naoReservado = true AND c is null)) ");
			sb.append("OR ((:reservado = true AND c is not null AND c.dataVencimento >= :dataHoje))) ");
		}

		for(int p = 0 ; p < comodos.size(); p++){
			sb.append(String.format("AND c%s.tipoComodo = :tp%s AND c%s.descricao = :comDescricao%s ", p, p, p, p));
		}
		
		sb.append("AND ((:isTodos = true) ");
		sb.append("OR c.dataVencimento >= :dataHoje ");
		sb.append("AND ( :isVenda = true AND (c.tipo = :tipoVenda OR c.tipo =:tipoVendaAluguel)) ");
		sb.append("OR (:isAluguel = true AND (c.tipo = :tipoAluguel OR c.tipo =:tipoVendaAluguel)) ");
		sb.append("OR (:isVendaAluguel = true))");
		
		sb = new StringBuilder(sb.toString().replaceAll("WHERE AND", "WHERE"));
		if(sb.toString().endsWith("WHERE "))
			sb = new StringBuilder(sb.toString().replaceAll("WHERE ", ""));
		
		TypedQuery<Imovel> typedQuery = em.createQuery(sb.toString(), Imovel.class);
		
		if(m.getCodigo() != null)
			typedQuery.setParameter("codigo", m.getCodigo());
		if(m.getMetroMin() != null)
			typedQuery.setParameter("metroMin", m.getMetroMin());
		if(m.getMetroMax() != null)
			typedQuery.setParameter("metroMax", m.getMetroMax());
		if(m.getValMin() != null)
			typedQuery.setParameter("valMin", m.getValMin());
		if(m.getValMax() != null)
			typedQuery.setParameter("valMax", m.getValMax());
		if(m.getAngariador() != null)
			typedQuery.setParameter("angariador", m.getAngariador());
		if(m.getProprietario() != null)
			typedQuery.setParameter("proprietario", m.getProprietario());
		if(m.getTipoImovel() != null)
			typedQuery.setParameter("tipoImovel", m.getTipoImovel());
		if(m.getBairro() != null)
			typedQuery.setParameter("bairro", m.getBairro());
		if(m.getCidade() != null)
			typedQuery.setParameter("cidade", m.getCidade());
		if(m.getObservacoes() != null)
			typedQuery.setParameter("observacoes", m.getObservacoes());
		if(!(m.isReservado() && m.isNaoReservado())){
			typedQuery.setParameter("naoReservado", m.isNaoReservado());
			typedQuery.setParameter("reservado", m.isReservado());
		}
		
		if(!comodos.isEmpty()){
			for(int p = 0 ; p < comodos.size(); p++){
				Comodo c = comodos.get(p);
				typedQuery.setParameter(String.format("tp%s", p), c.getTipoComodo());
				typedQuery.setParameter(String.format("comDescricao%s", p), c.getDescricao());				
			}
		}
		
		typedQuery.setParameter("dataHoje", dataHoje);
		typedQuery.setParameter("isAluguel", m.isLocacao());
		typedQuery.setParameter("isVenda", m.isVenda());
		typedQuery.setParameter("isVendaAluguel", m.isVendaLocacao());
		typedQuery.setParameter("isTodos", m.isTodos());
		typedQuery.setParameter("tipoVenda", TipoContrato.VENDA);
		typedQuery.setParameter("tipoAluguel", TipoContrato.LOCACAO);
		typedQuery.setParameter("tipoVendaAluguel", TipoContrato.LOCACAOVENDA);
		
		return typedQuery.getResultList();
	}

}
