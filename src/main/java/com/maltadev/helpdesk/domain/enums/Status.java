package com.maltadev.helpdesk.domain.enums;

public enum Status {

	ADMIN(0, "ABERTO"), CLIENTE(1, "ANDAMENTO"), TECNICO(2, "ENCERRADO");

	private Integer codigo;
	private String descricao;

	private Status(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Status toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(Status x : Status.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Status Inválido");
	}

}
