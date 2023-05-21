package br.com.fiap.cp03.entity;

import java.util.Calendar;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="HTE_CACHORRO")
@SequenceGenerator(sequenceName = "SQ_HTE_CACHORRO", name="cachorro", allocationSize = 1)
public class Cachorro {

	@Id
	@Column(name="ID_CACHORRO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cachorro")
	private Integer codigo;
	
	@Column(name="NOME", length = 50, nullable = false)
	private String nome;
	
	@Column(name="RACA", length = 50, nullable = false)
	private String raca;
	
	@Column(name="SEXO", length = 50, nullable = false)
	private String sexo;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_criacao", nullable = false, updatable = false)
	private Calendar dtCadastro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_atualizacao")
	private Calendar dtAtualizacao;
	
	public Cachorro() {}
	
	public Cachorro(String nome, String raca, String sexo, Calendar dtCadastro, Calendar dtAtualizacao) {
		super();
		this.nome = nome;
		this.raca = raca;
		this.sexo = sexo;
		this.dtCadastro = dtCadastro;
		this.dtAtualizacao = dtAtualizacao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Calendar getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Calendar dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Calendar getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(Calendar dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}
}
