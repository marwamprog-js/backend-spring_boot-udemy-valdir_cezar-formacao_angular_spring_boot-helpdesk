package com.maltadev.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maltadev.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
