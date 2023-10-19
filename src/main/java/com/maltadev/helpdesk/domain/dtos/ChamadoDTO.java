package com.maltadev.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maltadev.helpdesk.domain.Chamado;

public class ChamadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;
	
	@NotNull(message = "O Campo Prioridade é requerido")
	private Integer prioridade;
	
	@NotNull(message = "O Campo Status é requerido")
	private Integer status;
	
	@NotNull(message = "O Campo Observações é requerido")
	private String observacoes;
	
	@NotNull(message = "O Campo Titulo é requerido")
	@NotBlank(message = "O Campo Titulo é requerido")
	private String titulo;
	
	@NotNull(message = "O Campo Tecnico é requerido")
	private Long tecnico;
	
	@NotNull(message = "O Campo Cliente é requerido")
	private Long cliente;
	
	private String nomeTecnico;
	private String nomeCliente;

	public ChamadoDTO() {
		super();
	}

	public ChamadoDTO(Chamado obj) {
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.prioridade = obj.getPrioridade().getCodigo();
		this.status = obj.getStatus().getCodigo();
		this.observacoes = obj.getObservacoes();
		this.titulo = obj.getTitulo();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
		this.nomeTecnico = obj.getTecnico().getNome();
		this.nomeCliente = obj.getCliente().getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getTecnico() {
		return tecnico;
	}

	public void setTecnico(Long tecnico) {
		this.tecnico = tecnico;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	public String getNomeTecnico() {
		return nomeTecnico;
	}

	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

}
