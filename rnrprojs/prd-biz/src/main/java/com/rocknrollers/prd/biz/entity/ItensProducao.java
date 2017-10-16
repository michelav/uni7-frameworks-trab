package com.rocknrollers.prd.biz.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the itens_producao database table.
 * 
 */
@Entity
@Table(name="itens_producao")
@NamedQuery(name="ItensProducao.findAll", query="SELECT i FROM ItensProducao i")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ItensProducao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ITENS_PRODUCAO_TID_GENERATOR", sequenceName="ITENS_PRODUCAO_TID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ITENS_PRODUCAO_TID_GENERATOR")
	@XmlElement
	private Integer tid;

	@XmlElement
	private String camisa;

	@XmlElement
	private Integer quantidade;

	@XmlElement
	@Enumerated(EnumType.STRING)
	private Tamanho tamanho;

	//bi-directional many-to-one association to Producao
	@ManyToOne
	@JoinColumn(name="pid")
	private Producao producao;

	public ItensProducao() {
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

	public Producao getProducao() {
		return this.producao;
	}

	public void setProducao(Producao producao) {
		this.producao = producao;
	}

}

enum Tamanho {
	P, // Pequeno, 
	M, // Medio , 
	G // Grande
}