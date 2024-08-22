package com.ict.commons.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Immutable;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "view_perfil_em")
@Immutable
public class PerfilViewEM implements Serializable {

    private static final long serialVersionUID = 1L;

    public PerfilViewEM() {

    }

    public PerfilViewEM(Long id, Long perfil, String perfilNombre, Long tipoServicio, String tipoServicioNombre, String codigo,
            String identificacion, String nombre, String identificacionRepresentante,String nombreRepresentante, String provincia,
            String canton, String distrito, String direccion, Long numHab, Long numMaxHuespedes,String domicilioLegal,
            String telefono, String correo, Boolean aprobado, Boolean desafiliado,Boolean pendienteDesafiliar, Boolean rechazado,   
            Date fechaAprobacion, Date fechaDesafiliacion, Date fechaRechazo,String motivoDesafiliacion,String motivoRechazo, 
            Long empresa, Long inmueble, Long analista, Long jefe, Boolean editable) {
        super();
        this.id = id;
        this.perfil = perfil;
        this.perfilNombre = perfilNombre;
        this.tipoServicio = tipoServicio;
        this.tipoServicioNombre = tipoServicioNombre;
        this.codigo = codigo;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.identificacionRepresentante = identificacionRepresentante;
        this.nombreRepresentante = nombreRepresentante;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.direccion = direccion;
        this.numHab = numHab;
        this.numMaxHuespedes = numMaxHuespedes;
        this.domicilioLegal = domicilioLegal;
        this.telefono = telefono;
        this.correo = correo;
        this.aprobado = aprobado;
        this.desafiliado = desafiliado;
        this.pendienteDesafiliar = pendienteDesafiliar;
        this.rechazado = rechazado;
        this.fechaAprobacion = fechaAprobacion;
        this.fechaDesafiliacion = fechaDesafiliacion;
        this.fechaRechazo = fechaRechazo;
        this.motivoDesafiliacion = motivoDesafiliacion;
        this.motivoRechazo = motivoRechazo;
        this.empresa = empresa;
        this.inmueble = inmueble;
        this.analista = analista;
        this.jefe = jefe;
        this.editable = editable;
    }

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = false, example = "1", value = "Identificador autoincremental")
    @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    @Column(name = "id_perfil", nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "1", value = "Perfil")
    private Long perfil;

    @Column(name = "perfil_nombre", nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, value = "Perfil nombre")
    private String perfilNombre;

    @Column(name = "id_tipo_servicio", nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = true, value = "Tipo servicio")
    private Long tipoServicio;

    @Column(name = "tipo_servicio_nombre", nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = true, value = "Tipo servicio nombre")
    private String tipoServicioNombre;

    @Column(name = "codigo", nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = true, value = "Codigo del establecimiento")
    private String codigo;

    @Column(nullable = false, length = 255, name = "identificacion", insertable = false, updatable = false)
    @ApiModelProperty(required = true, example = "1-1111-1111", value = "Identificacion")
    private String identificacion;

    @Column(nullable = false, length = 80, name = "nombre", insertable = false, updatable = false)
    @ApiModelProperty(required = true, example = "Hector", value = "Nombre")
    private String nombre;

    @Column(nullable = true, length = 255, name = "identificacion_representante", insertable = false, updatable = false)
    @ApiModelProperty(required = true, example = "1-1111-1111", value = "Identificacion")
    private String identificacionRepresentante;

    @Column(nullable = true, length = 80, name = "nombre_representante", insertable = false, updatable = false)
    @ApiModelProperty(required = true, example = "Hector", value = "Nombre")
    private String nombreRepresentante;

    @Column(name = "provincia", length = 80, nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "Heredia", value = "Provincia")
    private String provincia;

    @Column(name = "canton", length = 80, nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "Heredia", value = "Canton")
    private String canton;

    @Column(name = "distrito", length = 200, nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "Heredia", value = "Distrito")
    private String distrito;

    @Column(name = "num_max_hues", length = 255, nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "Heredia", value = "Direccion")
    private Long numMaxHuespedes;
    
    @Column(name = "num_hab", length = 255, nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "1", value = "Direccion")
    private Long numHab;
    
    @Column(name = "domicilio_legal", length = 255, nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "Heredia", value = "Direccion")
    private String domicilioLegal;

    @Column(name = "direccion", length = 255, nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "Heredia", value = "Direccion")
    private String direccion;

    @Column(name = "telefono", length = 30, nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "22222222", value = "Telefono")
    private String telefono;

    @Column(name = "correo", length = 255, nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "abc@gmail.com", value = "Correo")
    private String correo;

    @Column(name = "aprobado", nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "True/False", value = "esta o no aprobado")
    private Boolean aprobado;

    @Column(name = "desafiliado", nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "True/False", value = "esta o no desafiliado")
    private Boolean desafiliado;

    @Column(name = "pendiente_desafiliar", nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "True/False", value = "esta o no pendiente de desafiliar")
    private Boolean pendienteDesafiliar;

    @Column(name = "rechazado", nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "True/False", value = "esta o no rechazado")
    private Boolean rechazado;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_aprobacion", nullable = false, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de probacion del registro")
    private Date fechaAprobacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_desafiliacion", nullable = false, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de desafiliacion del registro")
    private Date fechaDesafiliacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_rechazo", nullable = false, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "2020/01/01", value = "Fecha de rechazo del registro")
    private Date fechaRechazo;

    @Column(name = "motivo_desafiliacion", nullable = true, length = 500, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "Esto es un motivo", value = "Motivo de desafiliación")
    private String motivoDesafiliacion;

    @Column(name = "motivo_rechazo", nullable = true, length = 500, insertable = false, updatable = false)
    @ApiModelProperty(required = false, example = "Esto es un motivo", value = "Motivo de rechazo")
    private String motivoRechazo;

    @Column(name = "id_empresa", nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, value = "Empresa")
    private Long empresa;

    @Column(name = "id_loc_inmueble", nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, value = "Inmueble")
    private Long inmueble;

    @Column(name = "id_analista", nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, value = "Usuario")
    private Long analista;

    @Column(name = "id_jefe", nullable = true, insertable = false, updatable = false)
    @ApiModelProperty(required = false, value = "Jefe")
    private Long jefe;

    @ApiModelProperty(required = false, example = "true", value = "Editable")
    @Column(name = "editable", nullable = true, insertable = false, updatable = false)
    private Boolean editable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPerfil() {
        return perfil;
    }

    public void setPerfil(Long perfil) {
        this.perfil = perfil;
    }

    public String getPerfilNombre() {
        return perfilNombre;
    }

    public void setPerfilNombre(String perfilNombre) {
        this.perfilNombre = perfilNombre;
    }

    public Long getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(Long tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getTipoServicioNombre() {
        return tipoServicioNombre;
    }

    public void setTipoServicioNombre(String tipoServicioNombre) {
        this.tipoServicioNombre = tipoServicioNombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getIdentificacionRepresentante() {
        return identificacionRepresentante;
    }

    public void setIdentificacionRepresentante(String identificacionRepresentante) {
        this.identificacionRepresentante = identificacionRepresentante;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public Long getNumHab() {
        return numHab;
    }

    public void setNumHab(Long numHabitaciones) {
        this.numHab = numHabitaciones;
    }
    
    public Long getNumMaxHuespedes() {
        return numMaxHuespedes;
    }

    public void setNumMaxHuespedes(Long numMaxHuespedes) {
        this.numMaxHuespedes = numMaxHuespedes;
    }

    public String getDomicilioLegal() {
        return domicilioLegal;
    }

    public void setDomicilioLegal(String domicilioLegal) {
        this.domicilioLegal = domicilioLegal;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }

    public Boolean getDesafiliado() {
        return desafiliado;
    }

    public void setDesafiliado(Boolean desafiliado) {
        this.desafiliado = desafiliado;
    }

    public Boolean getPendienteDesafiliar() {
        return pendienteDesafiliar;
    }

    public void setPendienteDesafiliar(Boolean pendienteDesafiliar) {
        this.pendienteDesafiliar = pendienteDesafiliar;
    }

    public Boolean getRechazado() {
        return rechazado;
    }

    public void setRechazado(Boolean rechazado) {
        this.rechazado = rechazado;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Date getFechaDesafiliacion() {
        return fechaDesafiliacion;
    }

    public void setFechaDesafiliacion(Date fechaDesafiliacion) {
        this.fechaDesafiliacion = fechaDesafiliacion;
    }

    public Date getFechaRechazo() {
        return fechaRechazo;
    }

    public void setFechaRechazo(Date fechaRechazo) {
        this.fechaRechazo = fechaRechazo;
    }

    public String getMotivoDesafiliacion() {
        return motivoDesafiliacion;
    }

    public void setMotivoDesafiliacion(String motivoDesafiliacion) {
        this.motivoDesafiliacion = motivoDesafiliacion;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public Long getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Long empresa) {
        this.empresa = empresa;
    }

    public Long getInmueble() {
        return inmueble;
    }

    public void setInmueble(Long inmueble) {
        this.inmueble = inmueble;
    }

    public Long getAnalista() {
        return analista;
    }

    public void setAnalista(Long analista) {
        this.analista = analista;
    }

    public Long getJefe() {
        return jefe;
    }

    public void setJefe(Long jefe) {
        this.jefe = jefe;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

}