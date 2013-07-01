package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.com.michelon.softimob.aplicacao.service.ChaveService;
import br.com.michelon.softimob.aplicacao.service.ComodoService;
import br.com.michelon.softimob.aplicacao.service.ContratoPrestacaoServicoService;
import br.com.michelon.softimob.aplicacao.service.FeedbackService;
import br.com.michelon.softimob.aplicacao.service.PropostaService;
import br.com.michelon.softimob.aplicacao.service.ReservaService;

import com.google.common.collect.Lists;

@Entity
public class Imovel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private Boolean ativo = true;

	@Column
	private Integer metragem;

	@ManyToOne()
	private Funcionario angariador;

	@NotNull(message="O proprietário deve ser informado.")
	@ManyToOne(optional=false)
	private Cliente proprietario;
	
	@NotNull(message="O tipo de imóvel não foi informado")
	@ManyToOne(optional=false)
	private TipoImovel tipo;

	@Column
	private String observacoes;
	
	@OneToOne(cascade = CascadeType.ALL, optional=false)
	private Endereco endereco = new Endereco();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Foto> fotos = Lists.newArrayList();
	
	public List<ContratoPrestacaoServico> getContratos() {
		return new ContratoPrestacaoServicoService().findByImovel(this);
	}
	
	public List<Proposta> getPropostas() {
		return new PropostaService().findByImovel(this);
	}

	public List<Reserva> getReservas() {
		return new ReservaService().findByReserva(this);
	}

	public List<Comodo> getComodos() {
		return new ComodoService().findByImovel(this);
	}

	public List<Chave> getChaves() {
		return new ChaveService().findByImovel(this);
	}
	
	public List<Feedback> getFeedbacks() {
		return new FeedbackService().findByImovel(this);
	}
	
	public Cliente getProprietario() {
		return proprietario;
	}

	public Funcionario getAngariador() {
		return angariador;
	}

	public Integer getMetragem() {
		return metragem;
	}

	public void setMetragem(Integer metragem) {
		this.metragem = metragem;
	}

	public TipoImovel getTipo() {
		return tipo;
	}

	public void setTipo(TipoImovel tipo) {
		this.tipo = tipo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public List<Foto> getFotos() {
		return fotos;
	}
	
	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}
	
	@Override
	public String toString() {
		return this.id + " - " + this.endereco.toString() ;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imovel other = (Imovel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
