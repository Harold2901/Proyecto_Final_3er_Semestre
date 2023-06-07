package co.edu.unbosque.proyecto.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Attack {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Integer id;
	private String tipo;
	private String empresa;
	private Integer tiempo;
	private Integer afectados;
	private Date fecha;
	private String distrito;
	private Integer numagentes;
	private String agentes;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public Integer getTiempo() {
		return tiempo;
	}
	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}
	public Integer getAfectados() {
		return afectados;
	}
	public void setAfectados(Integer afectados) {
		this.afectados = afectados;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public Integer getNumagentes() {
		return numagentes;
	}
	public void setNumagentes(Integer numagentes) {
		this.numagentes = numagentes;
	}
	public String getAgentes() {
		return agentes;
	}
	public void setAgentes(String agentes) {
		this.agentes = agentes;
	}
}