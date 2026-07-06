package Modelo.Entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "correo")
	private String correo;

	@Column(name = "perfil")
	private String perfil;

	public Usuario() {
	}

	public Usuario(String nombre, String correo, String perfil) {
		this.nombre = nombre;
		this.correo = correo;
		this.perfil = perfil;
	}

	public Usuario(int id, String nombre, String correo, String perfil) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.perfil = perfil;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return this.nombre + " " + this.correo + " " + this.perfil;
	}

}
