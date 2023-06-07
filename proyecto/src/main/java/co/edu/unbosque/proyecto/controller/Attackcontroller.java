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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.proyecto.entities.Attack;
import co.edu.unbosque.proyecto.repository.Attackrepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/attack")
public class Attackcontroller {
	@Autowired
	private Attackrepository atr;
	
	@PostMapping(path="/add")
	public ResponseEntity<String> add(@RequestParam String tipo, @RequestParam String empresa, @RequestParam Integer tiempo, @RequestParam Integer afectados, @RequestParam String fecha, @RequestParam String distrito, @RequestParam String numagentes, @RequestParam String agentes){
		Attack at = new Attack();
		try {
			at.setTipo(tipo);
			at.setEmpresa(empresa);
			at.setTiempo(tiempo);
			at.setAfectados(afectados);
			at.setFecha(stringToDate(fecha));
			at.setDistrito(distrito);
			at.setNumagentes(Integer.parseInt(numagentes));
			at.setAgentes(agentes);
			atr.save(at);
			return ResponseEntity.status(HttpStatus.CREATED).body("Created");
		}catch(ParseException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Cannot Create");
		}
	}
	
	public ResponseEntity<ArrayList<Attack>> showAll(){
		ArrayList<Attack> all = atr.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(all);
	}
	
	@DeleteMapping(path="/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id){
		Optional<Attack> op = atr.findById(id);
		if(op.isPresent()) {
			atr.deleteById(id);
			return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not deleted");
	}
	
	@PutMapping(path="/upfecha/{id}")
	public ResponseEntity<String> upfecha(@RequestParam String tipo, @RequestParam String empresa, @RequestParam Integer tiempo, @RequestParam Integer afectados, @RequestParam String fecha, @RequestParam String distrito, @RequestParam String numagentes, @RequestParam String agentes, @PathVariable Integer id){
		Optional<Attack> op = atr.findById(id);
		if(!op.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
		}
		return op.map(at->{
			try {
				at.setTipo(tipo);
				at.setEmpresa(empresa);
				at.setTiempo(tiempo);
				at.setAfectados(afectados);
				at.setFecha(stringToDate(fecha));
				at.setDistrito(distrito);
				at.setNumagentes(Integer.parseInt(numagentes));
				at.setAgentes(agentes);
				atr.save(at);
				return ResponseEntity.status(HttpStatus.FOUND).body("Updated");
			}catch(ParseException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Cannot Update");
			}
		}).orElseGet(()->{
			Attack newattack = new Attack();
			try{
				newattack.setTipo(tipo);
				newattack.setEmpresa(empresa);
				newattack.setTiempo(tiempo);
				newattack.setAfectados(afectados);
				newattack.setFecha(stringToDate(fecha));
				newattack.setDistrito(distrito);
				newattack.setNumagentes(Integer.parseInt(numagentes));
				newattack.setAgentes(agentes);
				newattack.setId(id);
				atr.save(newattack);
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