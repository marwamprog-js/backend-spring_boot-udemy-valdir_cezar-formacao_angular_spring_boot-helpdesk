package com.maltadev.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.maltadev.helpdesk.domain.Pessoa;
import com.maltadev.helpdesk.domain.Cliente;
import com.maltadev.helpdesk.domain.dtos.ClienteDTO;
import com.maltadev.helpdesk.repositories.PessoaRepository;
import com.maltadev.helpdesk.repositories.ClienteRepository;
import com.maltadev.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.maltadev.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository tecnicoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Cliente findById(Long id) {
		Optional<Cliente> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return tecnicoRepository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));

		validaPorCpfEEmail(objDTO);

		Cliente newObj = new Cliente(objDTO);
		return tecnicoRepository.save(newObj);
	}

	public Cliente update(Long id, @Valid ClienteDTO dto) {
		dto.setId(id);

		Cliente oldObj = findById(id);

		if(!dto.getSenha().equals(oldObj.getSenha())) {
			dto.setSenha(encoder.encode(dto.getSenha()));
		}
		
		validaPorCpfEEmail(dto);

		oldObj = new Cliente(dto);

		return tecnicoRepository.save(oldObj);
	}

	public void delete(Long id) {
		Cliente obj = findById(id);

		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
		}

		tecnicoRepository.deleteById(id);
	}

	private void validaPorCpfEEmail(ClienteDTO objDTO) {

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
