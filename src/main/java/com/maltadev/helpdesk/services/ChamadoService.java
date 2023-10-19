package com.maltadev.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maltadev.helpdesk.domain.Chamado;
import com.maltadev.helpdesk.domain.Cliente;
import com.maltadev.helpdesk.domain.Tecnico;
import com.maltadev.helpdesk.domain.dtos.ChamadoDTO;
import com.maltadev.helpdesk.domain.enums.Prioridade;
import com.maltadev.helpdesk.domain.enums.Status;
import com.maltadev.helpdesk.repositories.ChamadoRepository;
import com.maltadev.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public Chamado findById(Long id) {
		Optional<Chamado> obj = chamadoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! ID: " + id));
	}

	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO chamadoDTO) {
		return chamadoRepository.save(newChamado(chamadoDTO));
	}
	
	public Chamado update(Long id, @Valid ChamadoDTO chamadoDTO) {
		chamadoDTO.setId(id);
		
		Chamado oldObj = findById(id);
		oldObj = newChamado(chamadoDTO);
		
		return chamadoRepository.save(oldObj);
	}
	
	private Chamado newChamado(ChamadoDTO chamadoDTO) {
		Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
		Cliente cliente = clienteService.findById(chamadoDTO.getCliente());
		
		Chamado chamado = new Chamado();
		
		if(chamadoDTO.getId() != null) {
			chamado.setId(chamadoDTO.getId());
		}
		
		if(chamadoDTO.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
		chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
		chamado.setTitulo(chamadoDTO.getTitulo());
		chamado.setObservacoes(chamadoDTO.getObservacoes());
		
		return chamado;
	}

	
}










