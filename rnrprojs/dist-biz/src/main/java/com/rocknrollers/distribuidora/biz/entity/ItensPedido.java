package com.rocknrollers.distribuidora.biz.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the itens_pedido database table.
 * 
 */
@Entity
@Table(name="itens_pedido")
@NamedQuery(name="ItensPedido.findAll", query="SELECT i FROM ItensPedido i")
public class ItensPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ITENS_PEDIDO_TID_GENERATOR", sequenceName="ITENS_PEDIDO_TID__SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ITENS_PEDIDO_TID_GENERATOR")
	private Integer tid;

	private String camisa;

	private Integer quantidade;

	@Enumerated(EnumType.STRING)
	private Tamanho tamanho;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="rid")
	private Pedido pedido;

	public ItensPedido() {
	}

	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
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