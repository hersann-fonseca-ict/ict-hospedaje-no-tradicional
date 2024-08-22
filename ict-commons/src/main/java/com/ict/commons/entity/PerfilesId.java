package com.ict.commons.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PerfilesId implements Serializable{
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion", updatable = false, nullable = false, insertable = true)
	@ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de creacion del registro")
	private Date fechaCreacion;
	
	@ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
	@Column(name = "id_perfil", nullable = false, unique = true)
	private Long idPerfil;
	
	@Column(name = "correo_perfil", nullable = false, length = 50)
	@ApiModelProperty(required = true, example = "prueba@gmail.com", value = "Correo")
	private String correo;
	
	@Column(name = "codigo", nullable = true, length = 255)
	@ApiModelProperty(required = false, example = "HNT0000001", value = "Codigo localización inmueble")
	private String codigoInmueble;

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCodigoInmueble() {
		return codigoInmueble;
	}

	public void setCodigoInmueble(String codigoInmueble) {
		this.codigoInmueble = codigoInmueble;
	}
	
	

}
