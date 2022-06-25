package com.example.springProject.repositories;

import com.example.springProject.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository(value = "repository")
public interface ClientRepository extends JpaRepository<Client, Long> {

}
