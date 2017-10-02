package es.monguerapps.ireferendum.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="consulta")
public class Consulta {

	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "idconsulta", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer idConsulta;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "pregunta", nullable = false)
	private String pregunta;
	@Column(name = "enlace_externo")
	private String enlaceExterno;
	@Column(name = "estado", nullable = false)
	private String estado;
	@Column(name = "tipo_respuesta")
	private Integer tipoRespuesta = 1;
	
	public Integer getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public Integer getTipoRespuesta() {
		return tipoRespuesta;
	}
	public void setTipoRespuesta(Integer tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}
	public String getEnlaceExterno() {
		return enlaceExterno;
	}
	public void setEnlaceExterno(String enlaceExterno) {
		this.enlaceExterno = enlaceExterno;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
