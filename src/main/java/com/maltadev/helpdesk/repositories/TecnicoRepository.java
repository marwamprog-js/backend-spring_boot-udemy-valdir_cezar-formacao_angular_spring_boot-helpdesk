package com.maltadev.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maltadev.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

}
