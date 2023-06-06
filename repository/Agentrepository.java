package co.edu.unbosque.proyecto.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.proyecto.entities.Agent;

public interface Agentrepository extends CrudRepository<Agent, Integer>{
	
	public Optional<Agent> findById(Integer id);
	
	public ArrayList<Agent> findAll();
}