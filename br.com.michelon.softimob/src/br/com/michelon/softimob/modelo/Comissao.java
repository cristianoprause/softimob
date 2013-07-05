package br.com.michelon.softimob.modelo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Comissao extends ContaPagarReceber{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Comissionado comissionado;
	
	@ManyToOne
	private VendaAluguel vendaAluguel;
	
	@SuppressWarnings("unused")
	private Comissao(){}
	
	public Comissao(Venda venda){
		this.vendaAluguel = venda;
		
		setTipo(ContaPagarReceber.PAGAR);
		setOrigem(ParametrosEmpresa.getInstance().getTipoContaComissao());
	}
	
	public Comissao(Aluguel aluguel){
		this.vendaAluguel = aluguel;
		
		setTipo(ContaPagarReceber.PAGAR);
		setOrigem(ParametrosEmpresa.getInstance().getTipoContaComissao());
	}
	
	public Comissionado getComissionado() {
		return comissionado;
	}

	public void setComissionado(Comissionado comissionado) {
		this.comissionado = comissionado;
	}

	public VendaAluguel getVendaAluguel() {
		return vendaAluguel;
	}

	public void setVendaAluguel(VendaAluguel vendaAluguel) {
		this.vendaAluguel = vendaAluguel;
	}

}
