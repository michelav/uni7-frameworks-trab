package com.rocknrollers.distribuidora.biz.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

import java.util.List;


/**
 * The persistent class for the pedidos database table.
 * 
 */
@Entity
@Table(name="pedidos")
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PEDIDOS_RID_GENERATOR", sequenceName="PEDIDOS_RID_SEQ ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PEDIDOS_RID_GENERATOR")
	private Integer rid;

	private String loja;

	//bi-directional many-to-one association to ItensPedido
	@OneToMany(mappedBy="pedido")
	private List<ItensPedido> itensPedidos;
	
	@XmlElement
	@Enumerated(EnumType.STRING)
	private SituacaoPedido situacao;

	//bi-directional many-to-one association to Pendencia
	@OneToMany(mappedBy="pedido")
	private List<Pendencia> pendencias;

	public Pedido() {
	}

	public Integer getRid() {
		return this.rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getLoja() {
		return this.loja;
	}

	public void setLoja(String loja) {
		this.loja = loja;
	}

	public List<ItensPedido> getItensPedidos() {
		return this.itensPedidos;
	}

	public void setItensPedidos(List<ItensPedido> itensPedidos) {
		this.itensPedidos = itensPedidos;
	}

	public ItensPedido addItensPedido(ItensPedido itensPedido) {
		getItensPedidos().add(itensPedido);
		itensPedido.setPedido(this);

		return itensPedido;
	}

	public ItensPedido removeItensPedido(ItensPedido itensPedido) {
		getItensPedidos().remove(itensPedido);
		itensPedido.setPedido(null);

		return itensPedido;
	}
	
	public SituacaoPedido getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoPedido situacao) {
		this.situacao = situacao;
	}


	public List<Pendencia> getPendencias() {
		return this.pendencias;
	}

	public void setPendencias(List<Pendencia> pendencias) {
		this.pendencias = pendencias;
	}

	public Pendencia addPendencia(Pendencia pendencia) {
		getPendencias().add(pendencia);
		pendencia.setPedido(this);

		return pendencia;
	}

	public Pendencia removePendencia(Pendencia pendencia) {
		getPendencias().remove(pendencia);
		pendencia.setPedido(null);

		return pendencia;
	}
}

enum SituacaoPedido {
	CRIADO,  
	CANCELADO,  
	PROCESSADO
}