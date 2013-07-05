package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class AcontecimentoChamado implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "A data não pode ser vazia")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new Date();
	
	@ManyToOne(optional=false)
	private Funcionario funcionario;
	
	@ManyToOne(optional = false)
	private ChamadoReforma chamadoReforma;
	
	@NotEmpty(message = "A descrição do acontecimento não pode ser vazia.")
	@Column(nullable=false)
	private String descricao;

	public AcontecimentoChamado(ChamadoReforma chamadoReforma){
		this.chamadoReforma = chamadoReforma;
	}
	
	@SuppressWarnings("unused")
	private AcontecimentoChamado(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public ChamadoReforma getChamadoReforma() {
		return chamadoReforma;
	}
	
	public void setChamadoReforma(ChamadoReforma chamadoReforma) {
		this.chamadoReforma = chamadoReforma;
	}
	
}
