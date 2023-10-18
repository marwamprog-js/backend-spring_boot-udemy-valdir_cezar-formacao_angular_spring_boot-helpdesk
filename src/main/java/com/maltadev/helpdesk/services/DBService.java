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
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "98427824092", "torvalds@mail.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}
	
}
