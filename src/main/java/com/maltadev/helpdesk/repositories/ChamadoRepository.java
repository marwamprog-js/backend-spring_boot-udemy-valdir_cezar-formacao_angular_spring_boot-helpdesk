package com.maltadev.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maltadev.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

}
