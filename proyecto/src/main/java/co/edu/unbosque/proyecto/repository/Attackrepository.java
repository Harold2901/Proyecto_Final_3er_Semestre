package co.edu.unbosque.proyecto.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.proyecto.entities.Attack;

public interface Attackrepository extends CrudRepository<Attack, Integer>{
	
	public Optional<Attack> findById(Integer id);
	
	public ArrayList<Attack> findAll();
}