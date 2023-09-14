package com.apibackend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apibackend.app.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long> {

}
