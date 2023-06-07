package co.edu.unbosque.proyecto.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.proyecto.entities.Agent;
import co.edu.unbosque.proyecto.repository.Agentrepository;

@CrossOrigin (origins = "*")
@RestController
@RequestMapping("/agent")
public class Agentcontroller {
	@Autowired
	private Agentrepository agr;
	
	@PostMapping(path="/add")
	public ResponseEntity<String> add(@RequestParam String nombre,@RequestParam String documento ,@RequestParam String nacimiento, @RequestParam String genero, @RequestParam String entrada, @RequestParam String rango){
		Agent ag = new Agent();
		try {
			ag.setNacimiento(stringToDate(nacimiento));
			ag.setNombre(nombre);
			ag.setDocumento(Integer.parseInt(documento));
			ag.setGenero(genero);
			ag.setEntrada(stringToDate(entrada));
			ag.setRango(rango);
			ag.setCasos(0);
			agr.save(ag);
			return ResponseEntity.status(HttpStatus.CREATED).body("Created");
		} catch (ParseException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Cannot Create");
		}
	}
	
	@GetMapping(path="/show")
	public ResponseEntity<ArrayList<Agent>> showAll(){
		ArrayList<Agent> all = agr.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(all);
	}
	
	@DeleteMapping(path="/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id){
		Optional<Agent> op = agr.findById(id);
		if(op.isPresent()) {
			agr.deleteById(id);
			return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
	}
	
	@PutMapping(path="/update/{id}")
	public ResponseEntity<String> update(@RequestParam String nombre,@RequestParam String documento ,@RequestParam String nacimiento, @RequestParam String genero, @RequestParam String entrada, @RequestParam String rango,@PathVariable Integer id){
		Optional<Agent> op = agr.findById(id);
		if(!op.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
		}
		return op.map(ag->{
			try {
				ag.setNombre(nombre);
				ag.setDocumento(Integer.parseInt(documento));
				ag.setNacimiento(stringToDate(nacimiento));
				ag.setGenero(genero);
				ag.setEntrada(stringToDate(entrada));
				ag.setRango(rango);
				agr.save(ag);
				return ResponseEntity.status(HttpStatus.FOUND).body("Updated");
			}catch(ParseException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Cannot Update");
			}
		}).orElseGet(()->{
			Agent newagent = new Agent();
			try {
				newagent.setNombre(nombre);
				newagent.setDocumento(Integer.parseInt(documento));
				newagent.setNacimiento(stringToDate(nacimiento));
				newagent.setGenero(genero);
				newagent.setEntrada(stringToDate(entrada));
				newagent.setRango(rango);
				newagent.setId(id);
				agr.save(newagent);
				return ResponseEntity.status(HttpStatus.CREATED).body("Created");
			}catch(ParseException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Cannot Create");
			}

		});
	}
	
	public Date stringToDate(String str) throws ParseException {
		Date aux = Date.valueOf(str);
		return aux;
	}
}