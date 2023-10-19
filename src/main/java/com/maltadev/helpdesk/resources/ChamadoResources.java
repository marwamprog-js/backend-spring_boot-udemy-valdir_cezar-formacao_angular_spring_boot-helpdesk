package com.maltadev.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maltadev.helpdesk.domain.Chamado;
import com.maltadev.helpdesk.domain.dtos.ChamadoDTO;
import com.maltadev.helpdesk.services.ChamadoService;

@RestController
@RequestMapping("/chamados")
public class ChamadoResources {

	@Autowired
	private ChamadoService chamadoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable("id") Long id) {
		Chamado obj = chamadoService.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}
	
}
