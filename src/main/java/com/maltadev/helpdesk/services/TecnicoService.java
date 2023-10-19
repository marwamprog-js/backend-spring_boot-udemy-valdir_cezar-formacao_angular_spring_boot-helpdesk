package com.maltadev.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maltadev.helpdesk.domain.Pessoa;
import com.maltadev.helpdesk.domain.Tecnico;
import com.maltadev.helpdesk.domain.dtos.TecnicoDTO;
import com.maltadev.helpdesk.repositories.PessoaRepository;
import com.maltadev.helpdesk.repositories.TecnicoRepository;
import com.maltadev.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.maltadev.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Long id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);

		validaPorCpfEEmail(objDTO);

		Tecnico newObj = new Tecnico(objDTO);
		return tecnicoRepository.save(newObj);
	}

	public Tecnico update(Long id, @Valid TecnicoDTO dto) {
		dto.setId(id);

		Tecnico oldObj = findById(id);
		validaPorCpfEEmail(dto);

		oldObj = new Tecnico(dto);

		return tecnicoRepository.save(oldObj);
	}

	public void delete(Long id) {
		Tecnico obj = findById(id);

		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
		}

		tecnicoRepository.deleteById(id);
	}

	private void validaPorCpfEEmail(TecnicoDTO objDTO) {

		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}

		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}

}
