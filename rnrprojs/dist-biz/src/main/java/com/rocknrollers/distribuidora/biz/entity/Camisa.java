package com.rocknrollers.distribuidora.biz.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the camisas database table.
 * 
 */
@Entity
@Table(name="camisas")
@NamedQueries(
		{@NamedQuery(name="Camisa.findAll", query="SELECT c FROM Camisa c"), 
		 @NamedQuery(name="Camisa.findByNameSize", 
				query="SELECT c FROM Camisa c WHERE c.nome = :nome AND c.tamanho = :tamanho")})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Camisa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CAMISAS_SID_GENERATOR", sequenceName="CAMISAS_SID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CAMISAS_SID_GENERATOR")
	@XmlElement
	private Integer sid;

	@XmlElement
	private String nome;

	@XmlElement
	private Integer quantidade;

	@XmlElement
	@Enumerated(EnumType.STRING)
	private Tamanho tamanho;
	
	public Camisa() {
	}

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
}

enum Tamanho {
	P, // Pequeno, 
	M, // Medio , 
	G // Grande
}