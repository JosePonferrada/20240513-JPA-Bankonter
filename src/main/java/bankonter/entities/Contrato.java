package bankonter.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the contrato database table.
 * 
 */
@Entity
@Table(name="contrato")
@NamedQuery(name="Contrato.findAll", query="SELECT c FROM Contrato c")
public class Contrato extends Entidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String descripcion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFirma;

	private float limite;

	private float saldo;

	//bi-directional many-to-one association to Tipocontrato
	@ManyToOne
	@JoinColumn(name="idTipoContrato")
	private Tipocontrato tipocontrato;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;

	public Contrato() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaFirma() {
		return this.fechaFirma;
	}

	public void setFechaFirma(Date fechaFirma) {
		this.fechaFirma = fechaFirma;
	}

	public float getLimite() {
		return this.limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}

	public float getSaldo() {
		return this.saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public Tipocontrato getTipocontrato() {
		return this.tipocontrato;
	}

	public void setTipocontrato(Tipocontrato tipocontrato) {
		this.tipocontrato = tipocontrato;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}