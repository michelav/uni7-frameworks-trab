package com.rocknrollers.distribuidora.biz.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pendencias database table.
 * 
 */
@Entity
@Table(name="pendencias")
@NamedQuery(name="Pendencia.findAll", query="SELECT p FROM Pendencia p")
public class Pendencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PENDENCIAS_PID_GENERATOR", sequenceName="PENDENCIAS_PID__SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PENDENCIAS_PID_GENERATOR")
	private Integer pid;

	private String camisa;

	private Integer quantidade;

	@Enumerated(EnumType.STRING)
	private Tamanho tamanho;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="rid")
	private Pedido pedido;

	public Pendencia() {
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getCamisa() {
		return this.camisa;
	}

	public void setCamisa(String camisa) {
		this.camisa = camisa;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Tamanho getTamanho() {
		return this.tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}