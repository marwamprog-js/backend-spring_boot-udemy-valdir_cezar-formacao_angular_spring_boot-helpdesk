package com.maltadev.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maltadev.helpdesk.domain.Chamado;
import com.maltadev.helpdesk.domain.Cliente;
import com.maltadev.helpdesk.domain.Tecnico;
import com.maltadev.helpdesk.domain.enums.Perfil;
import com.maltadev.helpdesk.domain.enums.Prioridade;
import com.maltadev.helpdesk.domain.enums.Status;
import com.maltadev.helpdesk.repositories.ChamadoRepository;
import com.maltadev.helpdesk.repositories.ClienteRepository;
import com.maltadev.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public void instanciaDB() {
		
		Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "64431239057", "valdir@mail.com", "123");
		tec1.addPerfis(Perfil.ADMIN);
		
		Tecnico tec2 = new Tecnico(null, "Marwam Malta", "18402326099", "marwam@mail.com", "123");
		Tecnico tec3 = new Tecnico(null, "Luciano Simoes", "08014999096", "luciano@mail.com", "123");
		Tecnico tec4 = new Tecnico(null, "Evelson Souza", "91032606070", "evelson@mail.com", "123");
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "98427824092", "torvalds@mail.com", "123");
		Cliente cli2 = new Cliente(null, "Debora Cristina", "09754277095", "debora@mail.com", "123");
		Cliente cli3 = new Cliente(null, "Lucineide Malta", "27886236076", "lucineide@mail.com", "123");
		Cliente cli4 = new Cliente(null, "Pedro Augusto", "53851218000", "pedro@mail.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}
	
}
